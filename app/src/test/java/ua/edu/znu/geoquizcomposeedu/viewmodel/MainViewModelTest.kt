package ua.edu.znu.geoquizcomposeedu.viewmodel

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class MainViewModelTest {
    private lateinit var repository: QuestionRepository

    @Before
    fun setUp() {
        val initialQuestions = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )
        val repositoryFlow = MutableStateFlow(initialQuestions)
        repository = mockk<QuestionRepository>()
        every { repository.getQuestionListState() } returns repositoryFlow
        every { repository.getQuestionBankSize() } returns initialQuestions.size
    }

    /**
     * Test that onNextQuestionButtonClick correctly updates the currentIndex in the MainViewModel.
     * Use runTest to run the test in a coroutine context, and launch a coroutine to collect the state flow.
     * After calling onNextQuestionButtonClick, verify that the currentIndex has been updated to the expected value.
     * Finally, cancel the collection job to avoid leaks.
     */
    @Test
    fun onNextQuestionButtonClick() = runTest {
        val viewModel = MainViewModel(repository)
        // Start collecting the state flow to ensure it updates correctly
        val job = launch { viewModel.mainScreenState.collect() }
        // Call the method to test
        viewModel.onNextQuestionButtonClick()
        // Verify that the currentIndex has been updated correctly
        val expectedState = viewModel.mainScreenState.value.copy(currentIndex = 1)
        assertEquals(expectedState, viewModel.mainScreenState.value)
        // Cancel the collection job to avoid leaks
        job.cancel()
    }
}
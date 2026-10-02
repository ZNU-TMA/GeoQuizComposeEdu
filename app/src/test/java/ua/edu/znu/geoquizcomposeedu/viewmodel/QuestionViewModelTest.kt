package ua.edu.znu.geoquizcomposeedu.viewmodel

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

@OptIn(ExperimentalCoroutinesApi::class)
class QuestionViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: QuestionRepository
    private lateinit var viewModel: QuestionViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = QuestionViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onAddQuestionClick_addsQuestionToRepository(): Unit = runTest {
        val question = Question(id = 1, questionText = "What is 2 + 2?", answer = true)
        coEvery { repository.addQuestion(question) } returns Unit

        viewModel.onAddQuestionClick(question)
        advanceUntilIdle()

        coVerify(exactly = 1) { repository.addQuestion(question) }
    }

    @Test
    fun onUpdateQuestionClick_updatesQuestionInRepository(): Unit = runTest {
        val question = Question(id = 1, questionText = "What is 3 + 3?", answer = true)
        coEvery { repository.updateQuestion(question) } returns Unit

        viewModel.onUpdateQuestionClick(question)
        advanceUntilIdle()

        coVerify(exactly = 1) { repository.updateQuestion(question) }
    }

    @Test
    fun onRemoveQuestionClick_removesQuestionFromRepository(): Unit = runTest {
        val question =
            Question(id = 1, questionText = "What is the capital of France?", answer = false)
        coEvery { repository.removeQuestion(question) } returns Unit

        viewModel.onRemoveQuestionClick(question)
        advanceUntilIdle()

        coVerify(exactly = 1) { repository.removeQuestion(question) }
    }
}

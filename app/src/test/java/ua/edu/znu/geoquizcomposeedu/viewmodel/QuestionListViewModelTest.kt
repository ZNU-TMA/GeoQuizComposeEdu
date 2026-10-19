package ua.edu.znu.geoquizcomposeedu.viewmodel


import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertSame
import org.junit.Test
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionListViewModelTest {

    @Test
    fun questionListFlow_exposesRepositoryStateFlow() {
        val repositoryFlow = MutableStateFlow(emptyList<Question>())
        val repository = mockk<QuestionRepository>()
        every { repository.getQuestionListState() } returns repositoryFlow

        val viewModel = QuestionListViewModel(repository)

        assertSame(repositoryFlow, viewModel.questionListFlow)
    }

    @Test
    fun questionListFlow_emitsRepositoryUpdates(): Unit = runTest {
        val initialQuestions = listOf(Question(id = 1, questionText = "Q1", answer = true))
        val repositoryFlow = MutableStateFlow(initialQuestions)
        val repository = mockk<QuestionRepository>()
        every { repository.getQuestionListState() } returns repositoryFlow

        val viewModel = QuestionListViewModel(repository)
        val updatedQuestions = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )

        viewModel.questionListFlow.test {
            Assert.assertEquals(initialQuestions, awaitItem())
            repositoryFlow.value = updatedQuestions
            Assert.assertEquals(updatedQuestions, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}

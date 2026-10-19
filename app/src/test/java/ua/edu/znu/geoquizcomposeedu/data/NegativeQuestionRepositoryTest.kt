package ua.edu.znu.geoquizcomposeedu.data

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the QuestionRepositoryImpl class focusing on negative scenarios.
 * This test class uses MockK to mock the QuestionDao and verifies the behavior of the repository methods
 * when invalid inputs are provided or when the StateFlow has not been collected yet.
 */
class NegativeQuestionRepositoryTest : QuestionRepositoryBaseTest() {

    /**
     * Tests that the getQuestionByIndex method throws an IndexOutOfBoundsException
     * when an invalid index is provided.
     */
    @Test(expected = IndexOutOfBoundsException::class)
    fun getQuestionByIndex_throwsExceptionForInvalidIndex(): Unit = runTest {
        questionRepository = createRepositoryWithQuestions(
            listOf(
                Question(id = 1, questionText = "Q1", answer = true),
                Question(id = 2, questionText = "Q2", answer = false),
                Question(id = 3, questionText = "Q3", answer = true)
            )
        )
        // stateIn updates asynchronously; wait until repository StateFlow has seeded values
        awaitQuestionsSeeded()
        val index = 5 // Invalid index (out of bounds)
        val expectedQuestion = Question(id = 2, questionText = "Q2", answer = false)

        val result = questionRepository.getQuestionByIndex(index)

        assertEquals(expectedQuestion, result)
    }

    /**
     * This test is for the scenario where the StateFlow has not been collected yet.
     */
    @Test(expected = NoSuchElementException::class)
    fun getQuestionByIndex_throwsExceptionWhenStateFlowHasNotCollectedYet() {
        questionsFlow.value = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )
        // Attempt to get a question by index before the StateFlow has been collected
        questionRepository.getQuestionByIndex(1)
    }

    /**
     * Tests that the addQuestion method completes within a specified timeout.
     * This test ensures that the addQuestion method does not hang indefinitely and completes in a timely.
     */
    //    @Test(timeout = 100)
    @Test(timeout = 300)
    fun addQuestion_checkTimeout(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        // Mock the DAO's addQuestion method to return Unit when called
        coEvery { questionDao.addQuestion(question) } returns Unit

        // Call the addQuestion method and verify that it completes within the timeout
        questionRepository.addQuestion(question)
        coVerify(exactly = 1) { questionDao.addQuestion(question) }
    }
}
package ua.edu.znu.geoquizcomposeedu.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

/**
 * Unit tests for the QuestionRepositoryImpl class focusing on negative scenarios.
 * This test class uses MockK to mock the QuestionDao and verifies the behavior of the repository methods
 * when invalid inputs are provided or when the StateFlow has not been collected yet.
 */
class NegativeQuestionRepositoryTest {

    private lateinit var questionDao: QuestionDao
    private lateinit var questionsFlow: MutableStateFlow<List<Question>>
    private lateinit var questionRepository: QuestionRepository

    @Before
    fun setUp() {
        // Reset the singleton instance before each test to ensure a clean state
        resetRepositorySingleton()
        // Mock the QuestionDao and set up a MutableStateFlow to simulate the database flow
        questionDao = mockk()
        questionsFlow = MutableStateFlow(emptyList())
        every { questionDao.getQuestions() } returns questionsFlow
        questionRepository = QuestionRepositoryImpl.getInstance(questionDao)
    }

    @After
    fun tearDown() {
        //Reset the singleton instance after each test to avoid side effects between tests
        resetRepositorySingleton()
    }

    /**
     * Tests that the getQuestionByIndex method throws an IndexOutOfBoundsException
     * when an invalid index is provided.
     */
    @Test(expected = IndexOutOfBoundsException::class)
    fun getQuestionByIndex_throwsExceptionForInvalidIndex() {
        val index = 5 // Invalid index, as we will only have 2 questions in the flow
        questionsFlow.value = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )

        questionRepository.getQuestionByIndex(index)
    }

    /**
     * This test is for the scenario where the StateFlow has not been collected yet.
     */
    @Test(expected = IndexOutOfBoundsException::class)
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
    @Test(timeout = 150)
    fun addQuestion_checkTimeout(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        // Mock the DAO's addQuestion method to return Unit when called
        coEvery { questionDao.addQuestion(question) } returns Unit

        // Call the addQuestion method and verify that it completes within the timeout
        questionRepository.addQuestion(question)
        coVerify(exactly = 1) { questionDao.addQuestion(question) }
    }

    /**
     * Resets the singleton instance of QuestionRepositoryImpl to null using reflection.
     * This is necessary to ensure that each test starts with a fresh instance of the repository.
     */
    private fun resetRepositorySingleton() {
        val instanceField: Field = QuestionRepositoryImpl::class.java.getDeclaredField("instance")
        instanceField.isAccessible = true
        instanceField.set(null, null)
    }
}
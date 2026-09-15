package ua.edu.znu.geoquizcomposeedu.data

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

/**
 * Unit tests for the QuestionRepositoryImpl class.
 * This test class uses MockK to mock the QuestionDao and verifies the behavior of the repository methods.
 */
class QuestionRepositoryTest {
    private lateinit var questionDao: QuestionDao
    private lateinit var questionRepository: QuestionRepository

    @Before
    fun setUp() {
        // Reset the singleton instance before each test to ensure a clean state
        resetRepositorySingleton()
        // Mock the QuestionDao
        questionDao = mockk()
        questionRepository = createRepositoryWithQuestions(emptyList())
    }

    @After
    fun tearDown() {
        // Reset the singleton instance after each test to avoid side effects between tests
        resetRepositorySingleton()
    }

    /**
     * Tests that the getQuestionByIndex method returns the correct question for a valid index.
     */
    @Test
    fun getQuestionByIndex_returnsQuestionForValidIndex(): Unit = runTest {
        questionRepository = createRepositoryWithQuestions(
            listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false),
            Question(id = 3, questionText = "Q3", answer = true)
            )
        )
        // stateIn updates asynchronously; wait until repository StateFlow has seeded values
        kotlinx.coroutines.yield()
        val index = 1
        val expectedQuestion = Question(id = 2, questionText = "Q2", answer = false)

        val result = questionRepository.getQuestionByIndex(index)

        assertEquals(expectedQuestion, result)
    }

    /**
     * Tests that the getQuestionBankSize method returns the size of the initial state flow.
     */
    @Test
    fun getQuestionBankSize_returnsInitialStateFlowSize(): Unit = runTest {
        questionRepository = createRepositoryWithQuestions(
            listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false),
            Question(id = 3, questionText = "Q3", answer = true)
            )
        )
        kotlinx.coroutines.yield()
        // The initial state flow is empty, so the size should be 0
        val result = questionRepository.getQuestionBankSize()
        assertEquals(3, result)
    }

    /**
     * Tests that the getQuestionListState method exposes the DAO flow correctly.
     */
    @Test
    fun getQuestionListState_exposesDaoFlow() {
        // The initial state flow is empty, so the value should be an empty list
        val result = questionRepository.getQuestionListState()
        assertEquals(emptyList<Question>(), result.value)
    }

    /**
     * Tests that the addQuestion method calls the DAO's addQuestion method.
     */
    @Test
    fun addQuestion_callsDaoAddQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        // Mock the DAO's addQuestion method to return Unit when called
        coEvery { questionDao.addQuestion(question) } returns Unit

        questionRepository.addQuestion(question)
        // Verify that the DAO's addQuestion method was called exactly once with the correct question
        coVerify(exactly = 1) { questionDao.addQuestion(question) }
    }

    /**
     * Tests that the updateQuestion method calls the DAO's updateQuestion method.
     */
    @Test
    fun updateQuestion_callsDaoUpdateQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1 updated", answer = false)
        // Mock the DAO's updateQuestion method to return Unit when called
        coEvery { questionDao.updateQuestion(question) } returns Unit

        questionRepository.updateQuestion(question)
        // Verify that the DAO's updateQuestion method was called exactly once with the correct question
        coVerify(exactly = 1) { questionDao.updateQuestion(question) }
    }

    /**
     * Tests that the removeQuestion method calls the DAO's removeQuestion method.
     */
    @Test
    fun removeQuestion_callsDaoRemoveQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        // Mock the DAO's removeQuestion method to return Unit when called
        coEvery { questionDao.removeQuestion(question) } returns Unit

        questionRepository.removeQuestion(question)
        // Verify that the DAO's removeQuestion method was called exactly once with the correct question
        coVerify(exactly = 1) { questionDao.removeQuestion(question) }
    }

    /**
     * Creates a QuestionRepository instance with the provided list of questions.
     * This method resets the singleton instance of QuestionRepositoryImpl to ensure a fresh instance for each test.
     *
     * @param questions The list of questions to initialize the repository with.
     * @return A new instance of QuestionRepositoryImpl initialized with the provided questions.
     */
    private fun createRepositoryWithQuestions(questions: List<Question>): QuestionRepository {
        resetRepositorySingleton()
        val questionsFlow = MutableStateFlow(questions)
        every { questionDao.getQuestions() } returns questionsFlow
        return QuestionRepositoryImpl.getInstance(questionDao)
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

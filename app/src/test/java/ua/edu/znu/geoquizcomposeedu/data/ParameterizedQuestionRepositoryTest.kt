package ua.edu.znu.geoquizcomposeedu.data

import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.lang.reflect.Field

/**
 * Parameterized unit tests for the QuestionRepositoryImpl class.
 * This test class uses MockK to mock the QuestionDao and verifies the behavior of the repository methods
 * for different indices and expected questions.
 */
@RunWith(Parameterized::class)
class ParameterizedQuestionRepositoryTest(
    private val index: Int,
    private val expectedQuestion: Question
) {

    private lateinit var questionDao: QuestionDao
    private lateinit var questionRepository: QuestionRepository

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "Test {index}: index={0}, expectedQuestion={1}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(0, Question(id = 1, questionText = "Q1", answer = true)),
                arrayOf(1, Question(id = 2, questionText = "Q2", answer = false)),
                arrayOf(2, Question(id = 3, questionText = "Q3", answer = true))
            )
        }
    }

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

    @Test
    fun getQuestionByIndex_returnsQuestion(): Unit = runTest {
        questionRepository = createRepositoryWithQuestions(
            listOf(
                Question(id = 1, questionText = "Q1", answer = true),
                Question(id = 2, questionText = "Q2", answer = false),
                Question(id = 3, questionText = "Q3", answer = true)
            )
        )
        // stateIn updates asynchronously; wait until repository StateFlow has seeded values
        kotlinx.coroutines.yield()

        val result = questionRepository.getQuestionByIndex(index)

        assertEquals(expectedQuestion, result)
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
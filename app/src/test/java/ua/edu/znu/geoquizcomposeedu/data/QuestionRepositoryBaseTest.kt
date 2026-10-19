package ua.edu.znu.geoquizcomposeedu.data

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.After
import org.junit.Before
import java.lang.reflect.Field

/**
 * Base test class for QuestionRepositoryImpl tests.
 * This class provides common setup and teardown methods for the repository tests.
 * It also includes utility methods to create a repository instance with a given list of questions
 * and to reset the singleton instance of the repository.
 */
abstract class QuestionRepositoryBaseTest {
    protected lateinit var questionDao: QuestionDao
    protected lateinit var questionRepository: QuestionRepository
    protected lateinit var questionsFlow: MutableStateFlow<List<Question>>

    @Before
    open fun setUpBase() {
        resetRepositorySingleton()
        questionDao = mockk()
        questionRepository = createRepositoryWithQuestions(emptyList())
    }

    @After
    open fun tearDownBase() {
        resetRepositorySingleton()
    }

    /**
     * Awaits until the question repository has seeded questions in its StateFlow.
     * This is useful for tests that need to ensure the repository has initial data before proceeding.
     * This method uses Turbine to collect from the StateFlow and waits until a non-empty list of questions is emitted.
     * It cancels the collection after receiving the first non-empty list of questions.
     */
    protected suspend fun awaitQuestionsSeeded() {
        questionRepository.getQuestionListState().test {
            // Turbine automatically subscribes here, triggering WhileSubscribed()
            // Wait until the StateFlow emits a non-empty list of questions
            var current = awaitItem() // often initial emptyList from stateIn
            while (current.isEmpty()) {
                current = awaitItem()
            }
            // At this point, we have a non-empty list of questions
            cancelAndIgnoreRemainingEvents()
        }
    }

    /**
     * Creates a QuestionRepository instance with the provided list of questions.
     * This method resets the singleton instance of QuestionRepositoryImpl to ensure a fresh instance for each test.
     *
     * @param questions The list of questions to initialize the repository with.
     * @return A new instance of QuestionRepositoryImpl initialized with the provided questions.
     */
    protected fun createRepositoryWithQuestions(questions: List<Question>): QuestionRepository {
        resetRepositorySingleton()
        questionsFlow = MutableStateFlow(questions)
        every { questionDao.getQuestions() } returns questionsFlow
        return QuestionRepositoryImpl.getInstance(questionDao)
    }

    /**
     * Resets the singleton instance of QuestionRepositoryImpl to null.
     * This method uses reflection to access the private static field "instance" and set it to null.
     * It is used to ensure that each test starts with a fresh instance of the repository.
     */
    private fun resetRepositorySingleton() {
        val instanceField: Field = QuestionRepositoryImpl::class.java.getDeclaredField("instance")
        instanceField.isAccessible = true
        instanceField.set(null, null)
    }
}

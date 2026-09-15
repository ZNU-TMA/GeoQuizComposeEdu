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

class QuestionRepositoryTest {
    private lateinit var questionDao: QuestionDao
    private lateinit var questionsFlow: MutableStateFlow<List<Question>>
    private lateinit var questionRepository: QuestionRepository

    @Before
    fun setUp() {
        resetRepositorySingleton()
        questionDao = mockk()
        questionsFlow = MutableStateFlow(emptyList())
        every { questionDao.getQuestions() } returns questionsFlow
        questionRepository = QuestionRepositoryImpl.getInstance(questionDao)
    }

    @After
    fun tearDown() {
        resetRepositorySingleton()
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun getQuestionByIndex_throwsExceptionWhenStateFlowHasNotCollectedYet() {
        questionsFlow.value = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )

        questionRepository.getQuestionByIndex(1)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun getQuestionByIndex_throwsExceptionForInvalidIndex() {
        questionsFlow.value = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false)
        )

        questionRepository.getQuestionByIndex(2)
    }

    @Test
    fun getQuestionBankSize_returnsInitialStateFlowSize() {
        questionsFlow.value = listOf(
            Question(id = 1, questionText = "Q1", answer = true),
            Question(id = 2, questionText = "Q2", answer = false),
            Question(id = 3, questionText = "Q3", answer = true)
        )

        val result = questionRepository.getQuestionBankSize()

        assertEquals(0, result)
    }

    @Test
    fun getQuestionListState_exposesDaoFlow() {
        val result = questionRepository.getQuestionListState()

        assertEquals(emptyList<Question>(), result.value)
    }

    @Test
    fun addQuestion_callsDaoAddQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        coEvery { questionDao.addQuestion(question) } returns Unit

        questionRepository.addQuestion(question)

        coVerify(exactly = 1) { questionDao.addQuestion(question) }
    }

    @Test
    fun updateQuestion_callsDaoUpdateQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1 updated", answer = false)
        coEvery { questionDao.updateQuestion(question) } returns Unit

        questionRepository.updateQuestion(question)

        coVerify(exactly = 1) { questionDao.updateQuestion(question) }
    }

    @Test
    fun removeQuestion_callsDaoRemoveQuestion(): Unit = runTest {
        val question = Question(id = 1, questionText = "Q1", answer = true)
        coEvery { questionDao.removeQuestion(question) } returns Unit

        questionRepository.removeQuestion(question)

        coVerify(exactly = 1) { questionDao.removeQuestion(question) }
    }

    private fun resetRepositorySingleton() {
        val instanceField: Field = QuestionRepositoryImpl::class.java.getDeclaredField("instance")
        instanceField.isAccessible = true
        instanceField.set(null, null)
    }
}

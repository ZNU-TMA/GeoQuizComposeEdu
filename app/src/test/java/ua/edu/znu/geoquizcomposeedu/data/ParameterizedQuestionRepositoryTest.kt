package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Parameterized unit tests for the QuestionRepositoryImpl class.
 * This test class uses MockK to mock the QuestionDao and verifies the behavior of the repository methods
 * for different indices and expected questions.
 */
@RunWith(Parameterized::class)
class ParameterizedQuestionRepositoryTest(
    private val index: Int,
    private val expectedQuestion: Question
) : QuestionRepositoryBaseTest() {

    companion object {
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

    /**
     * Tests that the getQuestionByIndex method returns the correct question for the provided index.
     * This test uses parameterized inputs to verify the behavior for different indices and expected questions.
     */
    @Test
    fun getQuestionByIndex_returnsQuestion(): Unit = runTest {
        questionRepository = createRepositoryWithQuestions(
            listOf(
                Question(id = 1, questionText = "Q1", answer = true),
                Question(id = 2, questionText = "Q2", answer = false),
                Question(id = 3, questionText = "Q3", answer = true)
            )
        )

        awaitQuestionsSeeded()

        val result = questionRepository.getQuestionByIndex(index)
        assertEquals(expectedQuestion, result)
    }
}
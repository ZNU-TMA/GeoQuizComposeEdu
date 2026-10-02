package ua.edu.znu.geoquizcomposeedu.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class QuestionDataSourceTest {
    private lateinit var dataSource: QuestionDataSource

    @Before
    fun setUp() {
        dataSource = QuestionDataSource()
    }

    @Test
    fun getQuestions_returnsInitialQuestionBank() {
        val questions = dataSource.getQuestions()

        assertEquals(6, questions.size)
        assertTrue(questions.any { it.questionText == "Канберра - це столиця Австралії" })
        assertTrue(questions.any { it.questionText == "Тихий океан більший, аніж Атлантичний океан" })
    }

    @Test
    fun addQuestion_addsNewQuestion() {
        val question = Question(id = 100, questionText = "A new question", answer = true)

        dataSource.addQuestion(question)

        assertTrue(dataSource.getQuestions().contains(question))
    }

    @Test
    fun addQuestion_doesNotAddDuplicateQuestion() {
        val question = Question(id = 100, questionText = "A new question", answer = true)
        dataSource.addQuestion(question)
        val sizeAfterFirstInsert = dataSource.getQuestions().size

        dataSource.addQuestion(question)

        assertEquals(sizeAfterFirstInsert, dataSource.getQuestions().size)
    }

    @Test
    fun updateQuestion_replacesQuestionWithMatchingId() {
        val original = Question(id = 100, questionText = "Original question", answer = true)
        val updated = Question(id = 100, questionText = "Updated question", answer = false)
        dataSource.addQuestion(original)

        dataSource.updateQuestion(updated)

        assertFalse(dataSource.getQuestions().contains(original))
        assertTrue(dataSource.getQuestions().contains(updated))
    }

    @Test
    fun updateQuestion_doesNothingWhenIdDoesNotExist() {
        val questionsBeforeUpdate = dataSource.getQuestions().toList()
        val missingQuestion = Question(id = 100, questionText = "Unknown question", answer = true)

        dataSource.updateQuestion(missingQuestion)

        assertEquals(questionsBeforeUpdate, dataSource.getQuestions())
    }

    @Test
    fun removeQuestion_removesQuestionsWithMatchingId() {
        val question = Question(id = 100, questionText = "Question to remove", answer = true)
        dataSource.addQuestion(question)

        dataSource.removeQuestion(question)

        assertFalse(dataSource.getQuestions().contains(question))
    }

    @Test
    fun removeQuestion_doesNothingWhenIdDoesNotExist() {
        val questionsBeforeRemoval = dataSource.getQuestions().toList()
        val missingQuestion = Question(id = 100, questionText = "Unknown question", answer = true)

        dataSource.removeQuestion(missingQuestion)

        assertEquals(questionsBeforeRemoval, dataSource.getQuestions())
    }
}

package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Represents information about a quiz question and also provides
 * methods for managing the list of questions.
 */
interface QuestionService {
    /**
     * Get the list of all questions and observe for changes in the list.
     */
    fun getAllQuestions(): StateFlow<List<Question>>

    /**
     * Remove the specified question from the list. As a result,
     * the flow returned by [getAllQuestions] will emit the updated list.
     */
    fun removeQuestion(question: Question)

    /**
     * Singleton creator for [QuestionService].
     */
    companion object {
        fun get(): QuestionService = QuestionServiceImpl
    }
}

// implementation - singleton
private object QuestionServiceImpl : QuestionService {
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_australia,
            answer = true
        ),
        Question(textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_oceans, answer = true),
        Question(
            textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_mideast,
            answer = false
        ),
        Question(textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_africa, answer = false),
        Question(
            textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_americas,
            answer = true
        ),
        Question(textResId = ua.edu.znu.geoquizcomposeedu.R.string.question_asia, answer = true)
    )

    private val questionsMutableStateFlow: MutableStateFlow<List<Question>> =
        MutableStateFlow(questions)

    override fun getAllQuestions(): StateFlow<List<Question>> {
        return questionsMutableStateFlow
    }

    override fun removeQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions - question }
    }
}
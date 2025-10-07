package ua.edu.znu.geoquizcomposeedu.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "QuestionRepository"

class QuestionRepository private constructor(private val questionDataSource: QuestionDataSource) {
    // Singleton pattern provides a single instance of the repository with application
    companion object {
        @Volatile
        private var INSTANCE: QuestionRepository? = null

        fun getInstance(): QuestionRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuestionRepository(QuestionDataSource()).also { INSTANCE = it }
            }
        }
    }

    fun getQuestions(): List<Question> {
        return questionDataSource.getQuestions()
    }

    fun getQuestionByIndex(index: Int): Question {
        return getQuestionByIndex(index)
    }

    fun getQuestionBankSize() = getQuestions().size

    // Using MutableStateFlow for observable question list
    // so that any changes to the list will be emitted to collectors
    private val questionsMutableStateFlow: MutableStateFlow<List<Question>> =
        MutableStateFlow(getQuestions())

    fun getAllQuestions(): StateFlow<List<Question>> {
        return questionsMutableStateFlow
    }

    fun addQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions + question }
    }

    fun updateQuestion(updatedQuestion: Question) {
        questionsMutableStateFlow.update { oldQuestions ->
            oldQuestions.map {
                if (it.id == updatedQuestion.id) updatedQuestion else it
            }
        }
        Log.d(TAG, "questionsMutableStateFlow: ${questionsMutableStateFlow.value}")
    }

    fun removeQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions - question }
    }
}
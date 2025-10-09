package ua.edu.znu.geoquizcomposeedu.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private const val TAG = "QuestionRepository"

class QuestionRepositoryImpl private constructor(private val questionDataSource: QuestionDataSource):QuestionRepository {
    // Singleton pattern provides a single instance of the repository with application
    companion object {
        @Volatile
        private var INSTANCE: QuestionRepositoryImpl? = null

        fun getInstance(): QuestionRepositoryImpl {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuestionRepositoryImpl(QuestionDataSource()).also { INSTANCE = it }
            }
        }
    }

    private fun getQuestions(): List<Question> {
        return questionDataSource.getQuestions()
    }

    override fun getQuestionByIndex(index: Int): Question {
        return getQuestions()[index]
    }

    override fun getQuestionBankSize() = getQuestions().size

    // Using MutableStateFlow for observable question list
    // so that any changes to the list will be emitted to collectors
    private val questionsMutableStateFlow: MutableStateFlow<List<Question>> =
        MutableStateFlow(getQuestions())

    override fun getQuestionListState(): StateFlow<List<Question>> {
        return questionsMutableStateFlow
    }

    override fun addQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions + question }
    }

    override fun updateQuestion(updatedQuestion: Question) {
        questionsMutableStateFlow.update { oldQuestions ->
            oldQuestions.map {
                if (it.id == updatedQuestion.id) updatedQuestion else it
            }
        }
        Log.d(TAG, "questionsMutableStateFlow: ${questionsMutableStateFlow.value}")
    }

    override fun removeQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions - question }
    }
}
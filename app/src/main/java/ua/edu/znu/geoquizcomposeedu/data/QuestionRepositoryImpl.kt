package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuestionRepositoryImpl private constructor(private val questionDataSource: QuestionDataSource) :
    QuestionRepository {
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
//    private val _questions = MutableStateFlow<List<Question>>(getQuestions())
//    val questionsFlow: StateFlow<List<Question>> = _questions

    override fun getQuestionListState(): StateFlow<List<Question>> {
        return questionsMutableStateFlow
    }

    override fun addQuestion(question: Question) {
        // Add to data source
        questionDataSource.addQuestion(question)
        questionsMutableStateFlow.update { oldQuestions ->
            if (oldQuestions.contains(question)) oldQuestions else oldQuestions + question
        }
    }

    override fun updateQuestion(updatedQuestion: Question) {
        // Update in data source
        questionDataSource.updateQuestion(updatedQuestion)
        // Update in StateFlow
        questionsMutableStateFlow.update { oldQuestions ->
            oldQuestions.map {
                if (it.id == updatedQuestion.id) updatedQuestion else it
            }
        }
    }

    override fun removeQuestion(question: Question) {
        // Remove from data source
        questionDataSource.removeQuestion(question)
        // Update StateFlow
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions - question }
//        _questions.value = _questions.value.filter { it.id != question.id }
    }
}
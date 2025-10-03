package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuestionRepository private constructor (private val questionDataSource: QuestionDataSource){

    companion object {
        @Volatile
        private var INSTANCE: QuestionRepository? = null

        fun getInstance(): QuestionRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: QuestionRepository(QuestionDataSource()).also { INSTANCE = it }
            }
        }
    }

    fun getQuestionByIndex(index: Int): Question {
        return questionDataSource.getQuestionBank()[index]
    }

    fun getQuestionBankSize() = questionDataSource.getQuestionBank().size

    // Using MutableStateFlow for observable question list
    // so that any changes to the list will be emitted to collectors
    private val questionsMutableStateFlow: MutableStateFlow<List<Question>> =
        MutableStateFlow(questionDataSource.getQuestionBank())

    fun getAllQuestions(): StateFlow<List<Question>> {
        return questionsMutableStateFlow
    }

    fun removeQuestion(question: Question) {
        questionsMutableStateFlow.update { oldQuestions -> oldQuestions - question }
    }

//    fun addQuestion(question: Question) {
//        questionsMutableStateFlow.update { oldQuestions -> oldQuestions + question }
//    }
//
//    fun updateQuestion(updatedQuestion: Question) {
//        questionsMutableStateFlow.update { oldQuestions ->
//            oldQuestions.map {
//                if (it.id == updatedQuestion.id) updatedQuestion else it
//            }
//        }
//    }
}
package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class QuestionRepository(val questionDataSource: QuestionDataSource){

    fun getQuestionByIndex(index: Int): Question {
        return questionDataSource.getQuestionBank()[index]
    }

    fun getQuestionBankSize() = questionDataSource.getQuestionBank().size

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
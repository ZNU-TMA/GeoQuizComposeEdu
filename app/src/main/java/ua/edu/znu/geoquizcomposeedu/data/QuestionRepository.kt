package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.StateFlow

interface QuestionRepository {
    fun getQuestionByIndex(index: Int): Question
    fun getQuestionBankSize(): Int
    fun getQuestionListState(): StateFlow<List<Question>>
//    fun getQuestionState(question: Question): StateFlow<Question>
    fun addQuestion(question: Question)
    fun updateQuestion(updatedQuestion: Question)
    fun removeQuestion(question: Question)
}
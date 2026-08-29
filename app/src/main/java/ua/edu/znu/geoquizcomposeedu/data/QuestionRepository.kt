package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.StateFlow

interface QuestionRepository {
    fun getQuestionByIndex(index: Int): Question
    fun getQuestionBankSize(): Int
    fun getQuestionListState(): StateFlow<List<Question>>
    suspend fun addQuestion(question: Question)
    suspend fun updateQuestion(updatedQuestion: Question)
    suspend fun removeQuestion(question: Question)
}
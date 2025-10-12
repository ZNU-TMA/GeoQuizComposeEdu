package ua.edu.znu.geoquizcomposeedu.data

import kotlinx.coroutines.flow.StateFlow

interface QuestionRepository {
    fun getQuestionByIndex(index: Int): Question
    fun getQuestionBankSize(): Int
    fun getQuestionListState(): StateFlow<List<Question>>
    /* We don't need to observe and react to changes in the question data
       from QuestionViewModel, so collecting the flow is unnecessary
       and we relying only on initialQuestion. */
//    fun getQuestionState(question: Question): StateFlow<Question>
    fun addQuestion(question: Question)
    fun updateQuestion(updatedQuestion: Question)
    fun removeQuestion(question: Question)
}
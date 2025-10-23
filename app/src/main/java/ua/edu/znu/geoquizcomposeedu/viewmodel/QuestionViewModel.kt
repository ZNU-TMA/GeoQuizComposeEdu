package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionViewModel(private val questionRepository: QuestionRepository) :
    ViewModel() {
    /* We don't need to observe and react to changes in the question data
       from QuestionViewModel, so collecting the flow is unnecessary
       and we relying only on initialQuestion. */
//    val questionFlow = questionRepository.getQuestionState(question)

    fun onAddQuestionClick(question: Question) = questionRepository.addQuestion(question)

    fun onUpdateQuestionClick(question: Question) = questionRepository.updateQuestion(question)

    fun onRemoveQuestionClick(question: Question) = questionRepository.removeQuestion(question)
}
package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionListViewModel(
    private val questionRepository: QuestionRepository) : ViewModel() {

    val questionFlow = questionRepository.getAllQuestions()

    fun onRemoveQuestionClick(question: Question) = questionRepository.removeQuestion(question)

//    fun onAddQuestionClick(question: Question) = questionRepository.addQuestion(question)
//
//    fun onUpdateQuestionClick(updatedQuestion: Question) = questionRepository.updateQuestion(updatedQuestion)
}
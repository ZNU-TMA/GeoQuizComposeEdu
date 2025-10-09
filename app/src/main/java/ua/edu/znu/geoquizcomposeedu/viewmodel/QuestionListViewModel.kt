package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionListViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    // Expose the question list as a StateFlow
    var questionListFlow = questionRepository.getQuestionListState()

    fun onAddQuestionClick(question: Question) = questionRepository.addQuestion(question)

    fun onUpdateQuestionClick(question: Question) = questionRepository.updateQuestion(question)

    fun onRemoveQuestionClick(question: Question) = questionRepository.removeQuestion(question)
}
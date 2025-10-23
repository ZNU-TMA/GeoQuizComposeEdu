package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

// Pass QuestionRepository as a constructor parameter to MainViewModel
// and assign it to a property.
class QuestionListViewModel(questionRepository: QuestionRepository) : ViewModel() {
    // Expose the question list as a StateFlow
    val questionListFlow = questionRepository.getQuestionListState()

//    fun onRemoveQuestionClick(question: Question) = questionRepository.removeQuestion(question)
}
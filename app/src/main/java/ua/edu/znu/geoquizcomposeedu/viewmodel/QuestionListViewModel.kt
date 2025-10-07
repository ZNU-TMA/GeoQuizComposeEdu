package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionListViewModel(private val questionRepository: QuestionRepository) : ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        _questions.value = questionRepository.getAllQuestions().value
    }

    fun addQuestion(id: Int, questionText: String, answer: Boolean) {
        val newQuestion = Question(id=id, questionText = questionText, answer = answer)
        questionRepository.addQuestion(newQuestion)
        loadQuestions() // Refresh the list
    }

    fun updateQuestion(question: Question) {
        questionRepository.updateQuestion(question)
        loadQuestions() // Refresh the list
    }

    fun removeQuestion(question: Question) {
        questionRepository.removeQuestion(question)
        loadQuestions() // Refresh the list
    }

    var questionListFlow = questionRepository.getAllQuestions()
//
    fun onRemoveQuestionClick(question: Question) = questionRepository.removeQuestion(question)
//
//    fun addQuestion(id: Int, questionText: String, answer: Boolean) {
//        val newQuestion = Question(id = id, questionText = questionText, answer = answer)
//        questionRepository.addQuestion(newQuestion)
//        questionListFlow = questionRepository.getAllQuestions() // Refresh the list
//    }
//
//    fun updateQuestion(question: Question) {
//        questionRepository.updateQuestion(question)
//        questionListFlow = questionRepository.getAllQuestions() // Refresh the list
//    }
//
//    fun removeQuestion(question: Question) {
//        questionRepository.removeQuestion(question)
//        questionListFlow = questionRepository.getAllQuestions() // Refresh the list
//    }
}
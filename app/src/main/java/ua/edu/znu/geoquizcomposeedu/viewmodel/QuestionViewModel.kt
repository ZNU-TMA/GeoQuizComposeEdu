package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

class QuestionViewModel(val question: Question) : ViewModel() {

    var questionFlow: MutableStateFlow<Question> =
        MutableStateFlow(question)

    private val questionRepository: QuestionRepository = QuestionRepository.getInstance()

    fun onAddQuestionClick(question: Question) = questionRepository.addQuestion(question)

//    fun onUpdateQuestionClick(updatedQuestion: Question) =
//        questionRepository.updateQuestion(updatedQuestion)

    fun onUpdateQuestionClick(updatedQuestion: Question) {
        questionRepository.updateQuestion(question)
        questionFlow.value = updatedQuestion
    }
}
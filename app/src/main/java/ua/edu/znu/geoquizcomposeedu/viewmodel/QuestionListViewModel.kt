package ua.edu.znu.geoquizcomposeedu.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreenState

class QuestionListViewModel(val questionRepository: QuestionRepository) : ViewModel() {
    private val _questionListScreenState = MutableStateFlow(QuestionListScreenState())
    val questionListScreenState: StateFlow<QuestionListScreenState> =
        _questionListScreenState.asStateFlow()

    fun getQuestionBankSize() = questionRepository.getQuestionBankSize()

    fun getQuestionByIndex(index: Int) = questionRepository.getQuestionByIndex(index)
}
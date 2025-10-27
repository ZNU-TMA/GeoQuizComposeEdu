package ua.edu.znu.geoquizcomposeedu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreenState

private const val TAG = "MainViewModel"

// Pass QuestionRepository as a constructor parameter to MainViewModel
// and assign it to a property.
class MainViewModel(private val questionRepository: QuestionRepository) : ViewModel() {
    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

//    private val questionBank = listOf(
//        Question(textResId = R.string.question_australia, answer = true),
//        Question(textResId = R.string.question_oceans, answer = true),
//        Question(textResId = R.string.question_mideast, answer = false),
//        Question(textResId = R.string.question_africa, answer = false),
//        Question(textResId = R.string.question_americas, answer = true),
//        Question(textResId = R.string.question_asia, answer = true)
//    )

//    private val questionBank: List<Question> = questionRepository.getQuestionBank()

    // Does not work, because the ViewModel’s state not updated yet
    // when the Composable recomposes.
    // It rely on a value that is not yet updated due to the asynchronous nature
    // of state updates in Compose.
//    fun getCurrentQuestionId() = questionBank[_mainScreenState.value.currentIndex].textResId

    var currentIndex = _mainScreenState.value.currentIndex

    // Works, because the updated in Composable index is passed as a parameter
    fun getQuestionByIndex(index: Int): Question {
        return questionRepository.getQuestionByIndex(index)
    }

    fun onAnswerButtonClick(isTrue: Boolean): Int {
        val currentQuestion = questionRepository.getQuestionByIndex(currentIndex)
        val messageId = if (isTrue == currentQuestion.answer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        return messageId
    }

    fun onNextQuestionButtonClick() {
        _mainScreenState.value = _mainScreenState.value.copy(
            currentIndex = (currentIndex + 1) % questionRepository.getQuestionBankSize()
        )
        currentIndex = _mainScreenState.value.currentIndex
        Log.d(TAG, "MainViewModel.onNextQuestionButtonClick currentIndex: $currentIndex")
    }

    // Works correctly because it may is a pure function
    // that always reflects the current state.
//    fun isLastQuestion(): Boolean {
//        return currentIndex == questionRepository.getQuestionBankSize() - 1
//    }
}
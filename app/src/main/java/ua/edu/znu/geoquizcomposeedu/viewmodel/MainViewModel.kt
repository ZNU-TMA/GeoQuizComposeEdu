package ua.edu.znu.geoquizcomposeedu.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreenState

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {
    private val _mainScreenState = MutableStateFlow(MainScreenState())
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState.asStateFlow()

    private val questionBank = listOf(
        Question(textResId = R.string.question_australia, answer = true),
        Question(textResId = R.string.question_oceans, answer = true),
        Question(textResId = R.string.question_mideast, answer = false),
        Question(textResId = R.string.question_africa, answer = false),
        Question(textResId = R.string.question_americas, answer = true),
        Question(textResId = R.string.question_asia, answer = true)
    )

    fun getCurrentQuestionId() = questionBank[_mainScreenState.value.currentIndex].textResId

    fun onAnswerButtonClick(context: Context, isTrue: Boolean) {
        val currentQuestion = questionBank[_mainScreenState.value.currentIndex]
        val message = if (isTrue == currentQuestion.answer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun onNextQuestionButtonClick() {
        _mainScreenState.value = _mainScreenState.value.copy(
            currentIndex = (_mainScreenState.value.currentIndex + 1) % questionBank.size
        )
        Log.d(TAG, "onNextQuestionButtonClick: ${_mainScreenState.value.currentIndex}")
    }

    fun isLastQuestion(): Boolean {
        return _mainScreenState.value.currentIndex == questionBank.size - 1
    }
}
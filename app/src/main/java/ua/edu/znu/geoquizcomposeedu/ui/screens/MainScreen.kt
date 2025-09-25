package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.parcelize.Parcelize
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.util.logCompositionLifecycle
import ua.edu.znu.geoquizcomposeedu.viewmodel.MainViewModel

private const val TAG = "MainScreen"

@Parcelize
data class MainScreenState(
    val currentIndex: Int = 0
) : Parcelable  // If state content are primitive types or String,
// you can implement Parcelable instead of Serializable
// Parcelable is more efficient than Serializable
// to save and restore state in Bundle

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
) {
    val mainViewModel: MainViewModel = viewModel()

//    val questionBank = listOf(
//        Question(R.string.question_australia, true),
//        Question(R.string.question_oceans, true),
//        Question(R.string.question_mideast, false),
//        Question(R.string.question_africa, false),
//        Question(R.string.question_americas, true),
//        Question(R.string.question_asia, true)
//    )

    // Save state in the Bundle across configuration changes
//    var mainScreenState by rememberSaveable { mutableStateOf(MainScreenState()) }

    // Use StateFlow in ViewModel to hold screen state
    // and collect it as State in Composable
    val mainScreenState by mainViewModel.mainScreenState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    logCompositionLifecycle("MainScreen")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = mainViewModel.getCurrentQuestionId()),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
        Row(
            modifier = Modifier.size(height = 90.dp, width = 200.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    mainViewModel.onAnswerButtonClick(context, true)
                }
            ) {
                Text(stringResource(id = R.string.true_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    mainViewModel.onAnswerButtonClick(context, false)
                }) {
                Text(stringResource(id = R.string.false_button))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
//            // Use delegate getter and setter for state access
//            mainScreenState =
//                mainScreenState.copy((mainScreenState.currentIndex + 1) % questionBank.size)
            mainViewModel.onNextQuestionButtonClick()
            Log.d(TAG, "MainScreen: currentIndex = ${mainScreenState.currentIndex}")
        }) {
            Text(stringResource(id = R.string.next_button))
        }
        Box(
            modifier = Modifier.height(100.dp)
        ) {
            if (mainViewModel.isLastQuestion()) {
                logCompositionLifecycle("LastQuestionText")
                Text(
                    text = "This is the last question"
                )
            }
        }
    }
}

//private fun showToast(context: Context, @StringRes resId: Int) {
//    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
//}

//private fun checkAnswer(
//    userAnswer: Boolean,
//    question: Question
//): Int {
//    return if (userAnswer == question.answer) {
//        R.string.correct_toast
//    } else {
//        R.string.incorrect_toast
//    }
//}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val innerPadding = PaddingValues(16.dp)

    MainScreen(innerPadding)
}
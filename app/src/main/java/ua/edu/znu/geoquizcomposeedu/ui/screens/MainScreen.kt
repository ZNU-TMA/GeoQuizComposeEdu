package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ua.edu.znu.geoquizcomposeedu.GeoQuizApplication
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme
import ua.edu.znu.geoquizcomposeedu.viewmodel.MainViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.ViewModelFactory

private const val TAG = "MainScreen"

data class MainScreenState(
    val currentIndex: Int = 0,
    val questionList: List<Question> = emptyList()
)

@Composable
fun MainScreen(
//    innerPadding: PaddingValues, // moved to NavHost
    snackbarHostState: SnackbarHostState,
) {
    val questionRepository =
        (LocalContext.current.applicationContext as GeoQuizApplication).questionRepository

    val mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(MainViewModel::class.java) {
            MainViewModel(questionRepository)
        }
    )

    // Use StateFlow in ViewModel to collect screen state as State in Composable
    val mainScreenState by mainViewModel.mainScreenState.collectAsStateWithLifecycle()

//    logCompositionLifecycle("MainScreen")

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = mainViewModel.getQuestionByIndex(mainScreenState.currentIndex).questionText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
        )
        Row(
            modifier = Modifier.size(height = 80.dp, width = 200.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val messageId = mainViewModel.onAnswerButtonClick(true)
//                    Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(messageId)
                        )
                    }
                },
            ) {
                Text(stringResource(id = R.string.true_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    val messageId = mainViewModel.onAnswerButtonClick(false)
//                    Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(messageId),
                        )
                    }
                },
            ) {
                Text(stringResource(id = R.string.false_button))
            }
        }
        Button(onClick = {
            mainViewModel.onNextQuestionButtonClick()
            Log.d(TAG, "MainScreen: currentIndex = ${mainScreenState.currentIndex}")
        }) {
            Text(stringResource(id = R.string.next_button))
        }
//        Box(
//            modifier = Modifier.height(80.dp)
//        ) {
//            if (mainViewModel.isLastQuestion()) {
//                logCompositionLifecycle("LastQuestionText")
//                Text(
//                    text = "This is the last question"
//                )
//            }
//        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    GeoQuizComposeEduTheme(darkTheme = true) {
        val snackbarHostState = SnackbarHostState()
        MainScreen(
            snackbarHostState = snackbarHostState
        )
    }
}
package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionListViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionViewModel

private const val TAG = "QuestionScreen"

@Composable
fun QuestionScreen(
    innerPadding: PaddingValues,
    question: Question,
//    questionListViewModel: QuestionListViewModel,
//    onBack: () -> Unit
) {
    val questionViewModel: QuestionViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return QuestionViewModel(question) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )
    // Collect the question state from the ViewModel and return the question
    // because of delegate using by.
    val currentQuestion by questionViewModel.questionFlow.collectAsStateWithLifecycle()

//    var textValue: String by remember { mutableStateOf("") }
//    var state: Boolean by remember { mutableStateOf(false) }

    val localFocusManager = LocalFocusManager.current

//    var textValue by rememberSaveable { mutableStateOf(question.questionText) }
//    var state by rememberSaveable { mutableStateOf(question.answer) }

    var textValue by remember { mutableStateOf(currentQuestion.questionText) }
    var state by remember { mutableStateOf(currentQuestion.answer) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = { updatedText ->
                textValue = updatedText
            },
            label = { Text(text = stringResource(R.string.questions)) },
//            value = currentQuestion.questionText,
//            label = { Text(text = stringResource(R.string.questions)) },
//            onValueChange = { updatedText ->
//                questionViewModel.onUpdateQuestionClick(
//                    currentQuestion.copy(questionText = updatedText)
//                )
//            },
//        singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Is answer True?")
            Spacer(modifier = Modifier.padding(8.dp))
            Checkbox(
                checked = state,
                onCheckedChange = { isChecked ->
                    state = isChecked
                },
//                checked = question.answer,
//                onCheckedChange = { isChecked ->
//                    questionViewModel.onUpdateQuestionClick(
//                        question.copy(answer = isChecked)
//                    )
//                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
//        Button(
//            onClick = {
//                val newQuestion = Question(
//                    id = currentQuestion.id,
//                    questionText = textValue,
//                    answer = state
//                )
//                questionViewModel.onAddQuestionClick(newQuestion)
//                Log.d(
//                    TAG,
//                    "Question bank size: ${QuestionRepository.getInstance().getQuestionBankSize()}"
//                )
//            },
//        ) {
//            Text(text = stringResource(R.string.add_question))
//        }
        Button(onClick = {
            questionViewModel.onUpdateQuestionClick(
                question.copy(
                    questionText = textValue,
                    answer = state
                )
            )
            Log.d(
                TAG,
                "Question bank size: ${QuestionRepository.getInstance().getQuestionByIndex(0)}"
            )
//            onBack()
        }) {
            Text("Update")
        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = {
//            questionViewModel.onRemoveQuestionClick(question)
//            Log.d(
//                TAG,
//                "Question bank size: ${QuestionRepository.getInstance().getQuestionBankSize()}"
//            )
////            onBack()
//        }) {
//            Text("Remove")
//        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//fun QuestionScreenPreview() {
//    val innerPadding = PaddingValues(16.dp)
//    val sampleQuestion = Question(
//        questionText = stringResource(R.string.question_australia),
//        answer = true
//    )
//    QuestionScreen(innerPadding, sampleQuestion)
//}

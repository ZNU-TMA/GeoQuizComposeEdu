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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepositoryImpl
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.ViewModelFactory

private const val TAG = "QuestionScreen"

@Composable
fun QuestionScreen(
    innerPadding: PaddingValues,
    initialQuestion: Question? = null,
    onSubmit: (Question) -> Unit,
    buttonTextRes: Int
) {
    val questionRepository = QuestionRepositoryImpl.getInstance()

    val questionViewModel: QuestionViewModel = viewModel(
        factory = ViewModelFactory(QuestionViewModel::class.java) {
//            QuestionViewModel(questionRepository, initialQuestion)
            QuestionViewModel(questionRepository)
        }
    )

    /* We don't need to observe and react to changes in the question data
       from QuestionViewModel, so collecting the flow is unnecessary
       and we relying only on initialQuestion. */
//    val questionState = questionViewModel.questionFlow.collectAsStateWithLifecycle()

    var textValue by rememberSaveable { mutableStateOf(initialQuestion?.questionText ?: "") }
    var state by rememberSaveable { mutableStateOf(initialQuestion?.answer ?: false) }

    val localFocusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            // To hide virtual keyboard when tapping outside TextField
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
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.is_answer_true))
            Spacer(modifier = Modifier.padding(8.dp))
            Checkbox(
                checked = state,
                onCheckedChange = { isChecked ->
                    state = isChecked
                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val question = initialQuestion?.copy(
                questionText = textValue,
                answer = state
            ) ?: Question(
                questionText = textValue,
                answer = state
            )
            if (initialQuestion == null) {
                questionViewModel.onAddQuestionClick(question)
            } else {
                // TODO: check if question was changed
                questionViewModel.onUpdateQuestionClick(question)
            }
            Log.d(
                TAG,
                "Question bank: ${questionRepository.getQuestionListState().value}"
            )
            onSubmit(question)
        }) {
            Text(stringResource(buttonTextRes))
        }
    }
}

@Composable
fun UpdateQuestionScreen(
    innerPadding: PaddingValues,
    question: Question,
    onBack: () -> Unit = {}
) {
    QuestionScreen(
        innerPadding = innerPadding,
        initialQuestion = question,
        onSubmit = { onBack() },
        buttonTextRes = R.string.update_question
    )
}

@Composable
fun AddQuestionScreen(
    innerPadding: PaddingValues,
    onBack: () -> Unit = {}
) {
    QuestionScreen(
        innerPadding = innerPadding,
        initialQuestion = null,
        onSubmit = { onBack() },
        buttonTextRes = R.string.add_question
    )
}

@Preview(showSystemUi = true)
@Composable
fun QuestionScreenPreview() {
    val innerPadding = PaddingValues(16.dp)
    val sampleQuestion = Question(
        questionText = stringResource(R.string.question_australia),
        answer = true
    )
    UpdateQuestionScreen(innerPadding, sampleQuestion)
}

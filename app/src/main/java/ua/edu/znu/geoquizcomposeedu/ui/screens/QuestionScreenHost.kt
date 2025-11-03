package ua.edu.znu.geoquizcomposeedu.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionViewModel

@Composable
fun QuestionScreenHost(
    initialQuestion: Question?,
    buttonTextRes: Int,
    questionViewModel: QuestionViewModel,
    onDone: () -> Unit,
    onRemoveDone: (() -> Unit)? = null
) {
    var textValue by rememberSaveable { mutableStateOf(initialQuestion?.questionText ?: "") }
    var isAnswerTrue by rememberSaveable { mutableStateOf(initialQuestion?.answer ?: false) }
    val onSave = {
        val question = initialQuestion?.copy(questionText = textValue, answer = isAnswerTrue)
            ?: Question(questionText = textValue, answer = isAnswerTrue)
        if (initialQuestion == null) questionViewModel.onAddQuestionClick(question)
        else questionViewModel.onUpdateQuestionClick(question)
        onDone()
    }
    val onRemove = initialQuestion?.let { q ->
        {
            questionViewModel.onRemoveQuestionClick(q)
            onRemoveDone?.invoke()
            Unit
        }
    }

    QuestionScreen(
        textValue = textValue,
        onTextChange = { textValue = it },
        isAnswerTrue = isAnswerTrue,
        onCheckedChange = { isAnswerTrue = it },
        buttonTextRes = buttonTextRes,
        onSave = onSave,
        onRemove = onRemove,
    )
}
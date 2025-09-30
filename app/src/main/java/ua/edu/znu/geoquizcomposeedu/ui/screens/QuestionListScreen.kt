package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.os.Parcelable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionDataSource
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepository

data class QuestionListScreenState(
    val questionList: List<Question> = emptyList()
)

@Composable
fun QuestionListScreen(
    innerPadding: PaddingValues,
) {
    val questionRepository = remember { QuestionRepository(QuestionDataSource()) }

    LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = questionRepository.getQuestionBankSize()
        ) { questionIndex ->
            val question = questionRepository.getQuestionByIndex(questionIndex)
            QuestionCard(
                questionText = question.textResId,
                questionAnswer = question.answer,
            )
        }
    }
}

@Composable
fun QuestionCard(
    questionText: Int,
    questionAnswer: Boolean,
    onQuestionClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onQuestionClick
            )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = stringResource(id = questionText))
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = questionAnswer,
                enabled = false,
                onCheckedChange = { /*TODO*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionCardPreview() {
    QuestionCard(
        questionText = R.string.question_australia,
        questionAnswer = true,
    )
}
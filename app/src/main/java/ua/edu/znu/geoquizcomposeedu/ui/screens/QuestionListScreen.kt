package ua.edu.znu.geoquizcomposeedu.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question

//private const val TAG = "QuestionListScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionListScreen(
//    navController: NavController,
    questionList: List<Question>,
    onEditQuestionClick: (Question) -> Unit = {},
    onAdd: () -> Unit = {}
) {
//    val questionRepository = (LocalContext.current.applicationContext as GeoQuizApplication).questionRepository
    // Using a generic ViewModelFactory to reduce boilerplate code
//    val questionListViewModel: QuestionListViewModel = viewModel(
//        factory = ViewModelFactory(QuestionListViewModel::class.java) {
//            QuestionListViewModel(questionRepository)
//        }
//    )
    // Use StateFlow in ViewModel to collect question list state as State in Composable.
//    val questionList: List<Question> by questionListViewModel.questionListFlow.collectAsStateWithLifecycle()

//    Log.d(TAG, "QuestionListScreen: questionList.size = ${questionList.size}")

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            listHeader()
            items(
                items = questionList,
                key = { question -> question.id }
            ) { question ->
                QuestionCard(
                    question,
                    onEditQuestionClick = {
                        onEditQuestionClick(question)
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = {
//                navController.navigate(Routes.QuestionAdd)
                onAdd()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_question))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.listHeader() {
    stickyHeader {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.question),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(start = 8.dp)
            )
            Text(
                text = stringResource(R.string.is_true),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun QuestionCard(
    question: Question,
    onEditQuestionClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = onEditQuestionClick
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = question.questionText,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Checkbox(
                checked = question.answer,
                enabled = false,
                onCheckedChange = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionCardPreview() {
    QuestionCard(
        question = Question(
            questionText = "Канберра - це столиця Австралії",
            answer = true
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun QuestionListScreenPreview() {
    QuestionListScreen(
//        navController = NavController(LocalContext.current),
        questionList = listOf(
            Question(questionText = stringResource(R.string.question_australia), answer = true),
            Question(questionText = stringResource(R.string.question_oceans), answer = true),
            Question(questionText = stringResource(R.string.question_mideast), answer = false),
        )
    )
}
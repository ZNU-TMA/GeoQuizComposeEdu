package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepositoryImpl
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionListViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.ViewModelFactory

private const val TAG = "QuestionListScreen"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionListScreen(
    innerPadding: PaddingValues,
) {
    val questionRepository = QuestionRepositoryImpl.getInstance()

//    val questionListViewModel: QuestionListViewModel = viewModel(
//        factory = object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                if (modelClass.isAssignableFrom(QuestionListViewModel::class.java)) {
//                    @Suppress("UNCHECKED_CAST")
//                    return QuestionListViewModel(questionRepository) as T
//                }
//                throw IllegalArgumentException("Unknown ViewModel class")
//            }
//        }
//    )

    // Using a generic ViewModelFactory to reduce boilerplate code
    val questionListViewModel: QuestionListViewModel = viewModel(
        factory = ViewModelFactory(QuestionListViewModel::class.java) {
            QuestionListViewModel(questionRepository)
        }
    )

    // Use StateFlow in ViewModel to collect question list state as State in Composable.
    val questionList: List<Question> by questionListViewModel.questionListFlow.collectAsStateWithLifecycle()

    Log.d(TAG, "QuestionListScreen: questionList.size = ${questionList.size}")

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            listHeader()
            items(
                items = questionList,
                key = { question -> question.id }
            ) { question ->
                QuestionCard(
                    question,
                    onQuestionDeleted = {
                        questionListViewModel.onRemoveQuestionClick(question)
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = { /* TODO: Navigate to add question screen */ },
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
                text = "Question",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(start = 8.dp)
            )
            Text(
                text = "Is true",
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun QuestionCard(
    question: Question,
    onQuestionDeleted: () -> Unit = {},
    onEditQuestionClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = question.questionText,
                modifier = Modifier.fillMaxWidth(0.65f)
            )
            Checkbox(
                checked = question.answer,
                enabled = false,
                onCheckedChange = {},
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = onEditQuestionClick) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit question",
                )
            }
            IconButton(
                onClick = onQuestionDeleted
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Edit question",
                )
            }
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
        innerPadding = PaddingValues(0.dp)
    )
}
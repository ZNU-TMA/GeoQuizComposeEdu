package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.ui.components.AppSnackbar
import ua.edu.znu.geoquizcomposeedu.ui.screens.AddQuestionScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.UpdateQuestionScreen
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    topBar = {
//                        TopAppBar()
                    },
                    floatingActionButton = {
//                        FloatingActionButton(
//                            onClick = { /* Handle FAB click */ }
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.Add,
//                                contentDescription = stringResource(R.string.add_question)
//                            )
//                        }
                    },
//                    floatingActionButtonPosition = FabPosition.End,
                    bottomBar = {
//                        BottomAppBar()
                    },
                    snackbarHost = {
                        AppSnackbar(snackbarHostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    // main content
//                    MainScreen(
//                        innerPadding = innerPadding,
//                        snackbarHostState = snackbarHostState
//                    )
//                    ComponentScreen(innerPadding)
//                    MultiComponentScreen(innerPadding)
//                    SimpleListExample(innerPadding)
//                    UserListExample(innerPadding)
//                    QuestionListScreen(innerPadding)
                    val sampleQuestion = Question(
                        questionText = stringResource(R.string.question_australia),
                        answer = true
//                        questionText = "",
//                        answer = false
                    )
                    UpdateQuestionScreen(innerPadding, sampleQuestion, {})
//                    AddQuestionScreen(innerPadding, {})
                }
            }
        }
    }
}

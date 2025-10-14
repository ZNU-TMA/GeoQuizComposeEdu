package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.data.QuestionRepositoryImpl
import ua.edu.znu.geoquizcomposeedu.ui.components.AppFloatingActionButton
import ua.edu.znu.geoquizcomposeedu.ui.components.AppSnackbar
import ua.edu.znu.geoquizcomposeedu.ui.components.BottomAppBar
import ua.edu.znu.geoquizcomposeedu.ui.components.GeiQuizTopAppBar
import ua.edu.znu.geoquizcomposeedu.ui.screens.AddQuestionScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.UpdateQuestionScreen
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme(
//                darkTheme = true
            ) {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    topBar = {
                        GeiQuizTopAppBar()
                    },
                    floatingActionButton = {
                    /* It need only on the QuestionListScreen */
//                        AppFloatingActionButton(
//                            onFabClick = {/* TODO: Navigate to add question screen */ }
//                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    bottomBar = {
                        BottomAppBar()
                    },
                    snackbarHost = {
                        AppSnackbar(snackbarHostState = snackbarHostState)
                    },
                ) { innerPadding ->
                    // main content
                    MainScreen(
                        innerPadding = innerPadding,
                        snackbarHostState = snackbarHostState
                    )
//                    ComponentScreen(innerPadding)
//                    MultiComponentScreen(innerPadding)
//                    SimpleListExample(innerPadding)
//                    UserListExample(innerPadding)
//                    QuestionListScreen(innerPadding)
//                    val sampleQuestion = QuestionRepositoryImpl.getInstance().getQuestionByIndex(0)
//                    UpdateQuestionScreen(innerPadding, sampleQuestion)
//                    val sampleQuestion = Question(questionText = "", answer = false)
//                    AddQuestionScreen(innerPadding)
                    /* For Scaffold innerPadding study */
//                    Text(
//                        text = "Hello, World!",
//                        textAlign = TextAlign.Center,
//                        fontSize = 80.sp,
//                        lineHeight = 80.sp,
//                        modifier = Modifier
////                            .padding(innerPadding)
//                            .fillMaxSize()
//                            .wrapContentHeight(Alignment.Bottom)
//                            .padding(16.dp)
//                    )
                }
            }
        }
    }
}

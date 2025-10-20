package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import ua.edu.znu.geoquizcomposeedu.educational.navigation.NavAppPassCustomType
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme(
//                darkTheme = true
            ) {
//                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    topBar = {
//                        GeoQuizTopAppBar()
                    },
                    floatingActionButton = {
                        /* It need only on the QuestionListScreen */
//                        AppFloatingActionButton(
//                            onFabClick = {/* TODO: Navigate to add question screen */ }
//                        )
                    },
//                    floatingActionButtonPosition = FabPosition.End,
                    bottomBar = {
//                        BottomAppBar()
                    },
                    snackbarHost = {
//                        AppSnackbar(snackbarHostState = snackbarHostState)
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
//                    val sampleQuestion = QuestionRepositoryImpl.getInstance().getQuestionByIndex(0)
//                    UpdateQuestionScreen(innerPadding, sampleQuestion)
//                    val sampleQuestion = Question(questionText = "", answer = false)
//                    AddQuestionScreen(innerPadding)
                    NavAppPassCustomType(innerPadding)
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

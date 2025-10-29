package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import ua.edu.znu.geoquizcomposeedu.educational.UniDirectionalDataFlow
import ua.edu.znu.geoquizcomposeedu.nav.NavPassQuestion
import ua.edu.znu.geoquizcomposeedu.ui.components.AppSnackbar
import ua.edu.znu.geoquizcomposeedu.ui.components.BottomAppBar
import ua.edu.znu.geoquizcomposeedu.ui.components.GeoQuizTopAppBar
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    topBar = {
                        GeoQuizTopAppBar()
                    },
//                    floatingActionButton = {
//                        /* It need only on the QuestionListScreen */
//                        AppFloatingActionButton(
//                            onFabClick = {/* Implemented in QuestionListScreen */ }
//                        )
//                    },
//                    floatingActionButtonPosition = FabPosition.End,
                    bottomBar = {
                        BottomAppBar(navController)
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
//                    UniDirectionalDataFlow(innerPadding)
//                    SimpleListExample(innerPadding)
//                    UserListExample(innerPadding)
//                    QuestionListScreen(innerPadding)
//                    val sampleQuestion = QuestionRepositoryImpl.getInstance().getQuestionByIndex(0)
//                    UpdateQuestionScreen(innerPadding, sampleQuestion)
//                    val sampleQuestion = Question(questionText = "", answer = false)
//                    AddQuestionScreen(innerPadding)
//                    NavAppPassCustomType(innerPadding)
                    NavPassQuestion(innerPadding, snackbarHostState, navController)
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

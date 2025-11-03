package ua.edu.znu.geoquizcomposeedu.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ua.edu.znu.geoquizcomposeedu.GeoQuizApplication
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionScreenHost
import ua.edu.znu.geoquizcomposeedu.viewmodel.MainViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionListViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.QuestionViewModel
import ua.edu.znu.geoquizcomposeedu.viewmodel.ViewModelFactory
import kotlin.reflect.typeOf

@Composable
fun NavPassQuestion(
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController
) {
    val questionRepository =
        (LocalContext.current.applicationContext as GeoQuizApplication).questionRepository
    val onNavigateBack: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Routes.Home,
//        startDestination = Routes.QuestionList,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.Home> {
            val mainViewModel: MainViewModel = viewModel(
                factory = ViewModelFactory(MainViewModel::class.java) {
                    MainViewModel(
                        questionRepository
                    )
                }
            )
            val mainScreenState by mainViewModel.mainScreenState.collectAsStateWithLifecycle()
            MainScreen(
                snackbarHostState,
                mainScreenState,
                getQuestionText = { index -> mainViewModel.getQuestionByIndex(index).questionText },
                onAnswerButtonClick = { answer -> mainViewModel.onAnswerButtonClick(answer) },
                onNextQuestionButtonClick = { mainViewModel.onNextQuestionButtonClick() }
            )
        }
        composable<Routes.QuestionList> {
            val questionListViewModel: QuestionListViewModel = viewModel(
                factory = ViewModelFactory(QuestionListViewModel::class.java) {
                    QuestionListViewModel(questionRepository)
                }
            )
            val questionList: List<Question> by questionListViewModel.questionListFlow.collectAsStateWithLifecycle()
            QuestionListScreen(
                questionList = questionList,
                onEditQuestionClick = { question ->
                    navController.navigate(Routes.QuestionEdit(question))
                },
                onAdd = {
                    navController.navigate(Routes.QuestionAdd)
                }
            )
        }
        composable<Routes.QuestionEdit>(
            typeMap = mapOf(
                typeOf<Question>() to QuestionNavType.questionType,
            )
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Routes.QuestionEdit>()
            val initialQuestion: Question? = route.question
            val questionViewModel: QuestionViewModel = viewModel(
                factory = ViewModelFactory(QuestionViewModel::class.java) {
                    QuestionViewModel(questionRepository)
                }
            )
            QuestionScreenHost(
                initialQuestion = initialQuestion,
                buttonTextRes = R.string.update_question,
                questionViewModel = questionViewModel,
                onDone = onNavigateBack,
                onRemoveDone = onNavigateBack
            )
        }
        composable<Routes.QuestionAdd> {
            val questionViewModel: QuestionViewModel = viewModel(
                factory = ViewModelFactory(QuestionViewModel::class.java) {
                    QuestionViewModel(questionRepository)
                }
            )
            QuestionScreenHost(
                initialQuestion = null,
                buttonTextRes = R.string.add_question,
                questionViewModel = questionViewModel,
                onDone = onNavigateBack
            )
        }
    }
}

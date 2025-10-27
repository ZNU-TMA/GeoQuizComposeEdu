package ua.edu.znu.geoquizcomposeedu.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.data.Question
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionScreen
import kotlin.reflect.typeOf

@Composable
fun NavPassQuestion(
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
//        startDestination = Routes.QuestionList,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.Home> {
            MainScreen(snackbarHostState)
        }
        composable<Routes.QuestionList> {
            QuestionListScreen(
                navController,
                onEditQuestionClick = { question ->
                    navController.navigate(Routes.QuestionEdit(question))
                }
            )
        }
        composable<Routes.QuestionEdit>(
            typeMap = mapOf(
                typeOf<Question>() to QuestionNavType.questionType,
            )
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Routes.QuestionEdit>()
            QuestionScreen(
                initialQuestion = route.question,
                buttonTextRes = R.string.update_question,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<Routes.QuestionAdd> {
            QuestionScreen(
                initialQuestion = null,
                buttonTextRes = R.string.add_question,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
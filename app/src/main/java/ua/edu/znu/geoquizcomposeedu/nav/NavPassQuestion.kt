package ua.edu.znu.geoquizcomposeedu.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreen

@Composable
fun NavPassQuestion(innerPadding: PaddingValues, snackbarHostState: SnackbarHostState, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.Home> {
            MainScreen(innerPadding, snackbarHostState)
        }
        composable<Routes.QuestionList> {
            QuestionListScreen(
                innerPadding,
                onEditQuestionClick = { question ->
                    navController.navigate(Routes.QuestionDetail(question))
                }
            )
        }
//        composable<Routes.QuestionDetail> { backStackEntry ->
//            val route = backStackEntry.toRoute<Routes.QuestionDetail>()
//            QuestionScreen(
//                innerPadding = innerPadding,
//                question = route.question
//            )
//        }
    }
}
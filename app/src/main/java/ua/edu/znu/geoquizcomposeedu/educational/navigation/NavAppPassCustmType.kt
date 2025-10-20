package ua.edu.znu.geoquizcomposeedu.educational.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.Routes
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.SubjectNavType
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.FirstScreen
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.SecondScreen
import kotlin.reflect.typeOf

@Composable
fun NavAppPassCustomType(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.FirstScreen> {
            FirstScreen(
                onListItemClick = {
                    /* Navigate to second screen with primitive parameter pass
                       and add the second screen to NavController stack */
                        subject ->
                    navController.navigate(Routes.SecondScreen(subject))
                }
            )
        }

        composable<Routes.SecondScreen>(
            /*Custom type map for the custom type*/
            typeMap = mapOf(
                typeOf<Subject>() to SubjectNavType.subjectType,
            )
        ) { backStackEntry ->
            // unpacking the back stack entry - current navigation destination
            // to obtain the route
            val route = backStackEntry.toRoute<Routes.SecondScreen>()
            SecondScreen(
                //passing the custom primitive value to the screen
                subject = route.subject,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

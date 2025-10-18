package ua.edu.znu.geoquizcomposeedu.educational.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.Routes
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.FirstScreen
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.SecondScreen

@Composable
fun NavAppSimple(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.FirstScreen> {
            FirstScreen(
                onNavigateForward = {
                    /* Navigate to second screen and add it to NavController stack */
                    navController.navigate(Routes.SecondScreen)
                }
            )
        }

        composable<Routes.SecondScreen> {
            SecondScreen(
                onNavigateBack = {
                    /* Navigate to previous screen and remove current from NavController stack
                       navController.navigate(Routes.FirstScreen) pushes the duplicate FirstScreen
                       - we don't want that!
                     */
                    navController.popBackStack()
                }
            )
        }
    }

}
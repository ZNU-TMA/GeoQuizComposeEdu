package ua.edu.znu.geoquizcomposeedu.educational.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.Routes
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.FirstScreen
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.SecondScreen

@Composable
fun NavAppPassPrimitive(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.FirstScreen> {
            FirstScreen(
                onNavigateForward = {
                    /* Navigate to second screen with primitive parameter pass
                       and add the second screen to NavController stack */
                        customPrimitive ->
                    navController.navigate(Routes.SecondScreen(customPrimitive))
                }
            )
        }

        composable<Routes.SecondScreen> { backStackEntry ->
            // unpacking the back stack entry - current navigation destination
            // to obtain the route
            val route = backStackEntry.toRoute<Routes.SecondScreen>()
            /*Extract passed data value from the route*/
            Log.d("SecondScreen", route.customPrimitive)
            SecondScreen(
                //passing the custom primitive value to the screen
                customPrimitive = route.customPrimitive,
                onNavigateBack = {
                    navController.navigate(Routes.FirstScreen)
                }
            )
        }
    }
}

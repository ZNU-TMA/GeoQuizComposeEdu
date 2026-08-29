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
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.Routes
import ua.edu.znu.geoquizcomposeedu.educational.navigation.nav.SubjectNavType
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.FirstScreen
import ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens.SecondScreen
import kotlin.reflect.typeOf
import kotlin.system.measureTimeMillis

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
                onListItemClick = { subject ->
                    val durationMs = measureTimeMillis {
                        navController.navigate(Routes.SecondScreen(subject))
                    }
                    Log.d("NavTiming", "navigate took $durationMs ms")
                }
            )
        }

        composable<Routes.SecondScreen>(
            /*Now we use defined custom NavType instance with Navigation Graph
              as element of the Map<KType, NavType<*>> of  NavGraphBuilder.composable
              typeMap argument.*/
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

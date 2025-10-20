package ua.edu.znu.geoquizcomposeedu.educational.navigation.nav

import kotlinx.serialization.Serializable
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject

@Serializable
sealed class Routes {
    // pure data object without any primitive
    @Serializable
    // Extending Routes provides polymorphism for navigation routes,
    // eg. Routes.FirstScreen and Routes.SecondScreen can be treated uniformly.
    data object FirstScreen : Routes()

    // data class - because it has a custom primitive to pass between screens
    @Serializable
    data class SecondScreen(val subject: Subject) : Routes()
}
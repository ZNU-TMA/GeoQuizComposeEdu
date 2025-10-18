package ua.edu.znu.geoquizcomposeedu.educational.navigation.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object FirstScreen

    @Serializable
    object SecondScreen
}
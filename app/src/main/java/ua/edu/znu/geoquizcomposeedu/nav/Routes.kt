package ua.edu.znu.geoquizcomposeedu.nav

import kotlinx.serialization.Serializable
import ua.edu.znu.geoquizcomposeedu.data.Question

@Serializable
sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data object QuestionList : Routes()

    @Serializable
    data class QuestionEdit(val question: Question) : Routes()

    @Serializable
    data object QuestionAdd : Routes()
}
package ua.edu.znu.geoquizcomposeedu.data

import java.util.UUID

data class Question(
    val id: String = UUID.randomUUID().toString(),
    val questionText: String,
    val answer: Boolean
)
package ua.edu.znu.geoquizcomposeedu.educational.navigation.data

import kotlinx.serialization.Serializable
import java.util.UUID
import kotlin.String

@Serializable
data class Subject(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val value: Boolean
)

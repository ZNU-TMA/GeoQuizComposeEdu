package ua.edu.znu.geoquizcomposeedu.educational.navigation.data

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Category(
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
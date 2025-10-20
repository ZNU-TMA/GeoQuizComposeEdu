package ua.edu.znu.geoquizcomposeedu.educational.navigation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID
import kotlin.String

@Serializable
@Parcelize
data class Subject(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val value: Boolean,
    val category: Category
) : Parcelable

package ua.edu.znu.geoquizcomposeedu.educational.navigation.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Parcelize
data class Category(
    val id: String = UUID.randomUUID().toString(),
    val name: String
) : Parcelable
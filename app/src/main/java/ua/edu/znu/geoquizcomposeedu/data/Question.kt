package ua.edu.znu.geoquizcomposeedu.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
@Parcelize
data class Question(
    val id: String = UUID.randomUUID().toString(),
    val questionText: String,
    val answer: Boolean
) : Parcelable {
    // Override equals and hashCode to compare based on questionText and answer only
    // to avoid adding duplicates in the question list
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Question) return false
        return questionText == other.questionText && answer == other.answer
    }

    override fun hashCode(): Int {
        var result = questionText.hashCode()
        result = 31 * result + answer.hashCode()
        return result
    }
}
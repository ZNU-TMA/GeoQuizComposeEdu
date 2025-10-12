package ua.edu.znu.geoquizcomposeedu.data

import java.util.UUID

data class Question(
    val id: String = UUID.randomUUID().toString(),
    val questionText: String,
    val answer: Boolean
){
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
package ua.edu.znu.geoquizcomposeedu.educational.room.onetoone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey
    val profileId: Long,
    val bio: String,
    val userOwnerId: Long
)

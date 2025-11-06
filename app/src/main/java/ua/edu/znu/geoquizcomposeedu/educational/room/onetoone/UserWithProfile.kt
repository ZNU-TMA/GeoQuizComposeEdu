package ua.edu.znu.geoquizcomposeedu.educational.room.onetoone

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithProfile(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val profile: Profile
)

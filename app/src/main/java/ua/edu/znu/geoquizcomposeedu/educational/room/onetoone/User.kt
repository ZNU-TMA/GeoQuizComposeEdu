package ua.edu.znu.geoquizcomposeedu.educational.room.onetoone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Long,
    val name: String,
    val age: Int
)

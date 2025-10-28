package ua.edu.znu.geoquizcomposeedu.educational.room.data.onetomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    val name: String,
    val age: Int
)

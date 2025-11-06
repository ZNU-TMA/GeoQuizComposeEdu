package ua.edu.znu.geoquizcomposeedu.educational.room.manytomany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val courseId: Long = 0,
    val title: String
)

package ua.edu.znu.geoquizcomposeedu.educational.room.manytomany

import androidx.room.Entity
import androidx.room.Index

/**
 * Cross-reference entity to represent the many-to-many relationship
 * between Students and Courses.
 */
@Entity(
    tableName = "student_courses",
    primaryKeys = ["studentId", "courseId"],
    indices = [Index(value = ["courseId"])]
)
data class StudentCourseCrossRef(
    val studentId: Long,
    val courseId: Long
)

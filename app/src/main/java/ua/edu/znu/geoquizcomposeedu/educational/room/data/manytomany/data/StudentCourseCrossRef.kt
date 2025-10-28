package ua.edu.znu.geoquizcomposeedu.educational.room.data.manytomany.data

import androidx.room.Entity

/**
 * Cross-reference entity to represent the many-to-many relationship
 * between Students and Courses.
 */
@Entity(tableName = "student_courses", primaryKeys = ["studentId", "courseId"])
data class StudentCourseCrossRef(
    val studentId: Long,
    val courseId: Long
)

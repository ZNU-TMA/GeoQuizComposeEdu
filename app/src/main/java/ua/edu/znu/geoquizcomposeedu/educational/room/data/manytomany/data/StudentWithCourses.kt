package ua.edu.znu.geoquizcomposeedu.educational.room.data.manytomany.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithCourses(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        entity = Course::class,
        associateBy = Junction(
            StudentCourseCrossRef::class
        )
    )
    val courses: List<Course>
)

package ua.edu.znu.geoquizcomposeedu.educational.room.data.manytomany.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
    @Embedded
    val course: Course,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        entity = Student::class,
        associateBy = Junction(
            StudentCourseCrossRef::class
        )
    )
    val students: List<Student>
)

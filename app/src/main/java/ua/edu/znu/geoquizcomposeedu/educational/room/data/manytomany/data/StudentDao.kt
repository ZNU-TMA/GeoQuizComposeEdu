package ua.edu.znu.geoquizcomposeedu.educational.room.data.manytomany.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Insert
    suspend fun insertCourse(course: Course)

    @Insert
    suspend fun insertStudentCourseCrossRef(crossRef: StudentCourseCrossRef)

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    fun getStudentWithCourses(studentId: Long): Flow<StudentWithCourses>

    @Transaction
    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    fun getCourseWithStudents(courseId: Long): Flow<CourseWithStudents>
}
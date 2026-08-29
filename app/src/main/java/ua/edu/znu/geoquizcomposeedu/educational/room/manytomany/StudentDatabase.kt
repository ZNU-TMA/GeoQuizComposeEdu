package ua.edu.znu.geoquizcomposeedu.educational.room.manytomany

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class, Course::class, StudentCourseCrossRef::class],
    version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}
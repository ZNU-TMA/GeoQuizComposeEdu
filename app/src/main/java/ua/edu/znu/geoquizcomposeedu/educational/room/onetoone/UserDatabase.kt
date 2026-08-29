package ua.edu.znu.geoquizcomposeedu.educational.room.onetoone

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Profile::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
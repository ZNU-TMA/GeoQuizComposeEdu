package ua.edu.znu.geoquizcomposeedu.educational.room.onetomany

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Playlist::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
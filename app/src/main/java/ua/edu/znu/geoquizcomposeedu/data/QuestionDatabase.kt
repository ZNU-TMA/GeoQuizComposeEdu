package ua.edu.znu.geoquizcomposeedu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var instance: QuestionDatabase? = null
        fun getInstance(context: Context): QuestionDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    QuestionDatabase::class.java,
                    "question_database"
                )
//                    .createFromAsset("database/question_database.db")
                    .build()
                    .also { instance = it }
            }
        }
    }
}
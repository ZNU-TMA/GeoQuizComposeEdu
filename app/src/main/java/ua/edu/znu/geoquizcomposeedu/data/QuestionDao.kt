package ua.edu.znu.geoquizcomposeedu.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

    @Delete
    suspend fun removeQuestion(question: Question)

    @Query("SELECT * FROM questions")
    fun getQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM questions WHERE id = :index LIMIT 1")
    fun getQuestionByIndex(index: Int): Flow<Question> // Returns null if not found
}
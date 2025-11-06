package ua.edu.znu.geoquizcomposeedu.educational.room.onetomany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Insert
    suspend fun insertPlaylist(playlist: Playlist)

    // This method requires Room to run two queries,
    // so add the @Transaction annotation to this method
    // so that the whole operation is performed atomically
    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserWithPlaylists(userId: Long): Flow<UserWithPlaylists>
}
package ua.edu.znu.geoquizcomposeedu.educational.room.data.onetomany

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

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserWithPlaylists(userId: Long): Flow<UserWithPlaylists>
}
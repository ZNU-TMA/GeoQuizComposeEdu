package ua.edu.znu.geoquizcomposeedu.educational.room.data.onetomany

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "playlists",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["creatorId"],
            onDelete = CASCADE
        )
    ]
)
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val playlistId: Long = 0,
    val name: String,
    val creatorId: Long // Foreign key referencing User.userId
)

package ua.edu.znu.geoquizcomposeedu.educational.room.data.onetomany

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Data class representing a User along with their associated Playlists.
 * One-to-many relationship: One User can have multiple Playlists.
 */
data class UserWithPlaylists(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "creatorId"
    )
    val playlists: List<Playlist>
)

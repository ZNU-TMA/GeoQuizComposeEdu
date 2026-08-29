package ua.edu.znu.geoquizcomposeedu.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Entity(
    tableName = "questions",
    indices = [Index(value = ["question"], unique = true)]
)
@Serializable
@Parcelize
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "question")
    val questionText: String,
    val answer: Boolean
) : Parcelable
/*
  equals() and hashCode() are not required by Room.
   Room uses the primary key to identify and update entities, so persistence works
   without custom equality.
   Default data class implementations include all properties (including an auto-generated id).
 */
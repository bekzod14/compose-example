package uz.gita.composeexample2.data.source.room.entities

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
)

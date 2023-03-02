package uz.gita.composeexample2.data.source.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val groupId: Long
)
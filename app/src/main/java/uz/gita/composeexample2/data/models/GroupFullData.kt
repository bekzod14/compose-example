package uz.gita.composeexample2.data.models

import uz.gita.composeexample2.data.source.room.entities.GroupEntity

class GroupFullData(
    val id: Long,
    val name: String,
    val count: Int
) {
    fun toGroupEntity() = GroupEntity(id, name)
}
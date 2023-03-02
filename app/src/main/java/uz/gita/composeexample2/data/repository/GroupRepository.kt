package uz.gita.composeexample2.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

interface GroupRepository {

    suspend fun addGroup(data: GroupEntity)

    suspend fun updateGroup(data: GroupEntity)

    suspend fun deleteGroup(data: GroupEntity)

    fun getGroups(): Flow<List<GroupFullData>>

    fun getGroupsSynchronous(): List<GroupEntity>


}
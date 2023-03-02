package uz.gita.composeexample2.data.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.source.room.dao.GroupDao
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupDao: GroupDao
) : GroupRepository {

    override suspend fun addGroup(data: GroupEntity) = groupDao.add(data)

    override suspend fun updateGroup(data: GroupEntity) = groupDao.update(data)

    override suspend fun deleteGroup(data: GroupEntity) = groupDao.delete(data)

    override fun getGroups() = groupDao.getGroupsWithInfo()

    override fun getGroupsSynchronous(): List<GroupEntity> = groupDao.getGroups()

}
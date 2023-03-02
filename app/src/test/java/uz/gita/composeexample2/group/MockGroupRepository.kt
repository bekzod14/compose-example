package uz.gita.composeexample2.group

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.repository.GroupRepository
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

class MockGroupRepository : GroupRepository {

    val list = ArrayList<GroupEntity>()

    override suspend fun addGroup(data: GroupEntity) {
        if (data.name.isNotEmpty() && data.id > 0){
            list.add(data)
        }
    }

    override suspend fun updateGroup(data: GroupEntity) {
        for (i in 0 until list.size) {
            if (list[i].id == data.id) {
                list[i] = data
            }
        }
    }

    override suspend fun deleteGroup(data: GroupEntity) {
        list.removeIf { it.id == data.id }
    }

    override fun getGroups(): Flow<List<GroupFullData>> = flow {
        emit(list.map { GroupFullData(it.id, it.name, 0) })
    }

    override fun getGroupsSynchronous(): List<GroupEntity> = list

}
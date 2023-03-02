package uz.gita.composeexample2.data.source.room.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity

@Dao
interface GroupDao : BaseDao<GroupEntity> {

    @Query("SELECT * FROM GroupEntity")
    fun getGroups(): List<GroupEntity>

    @Query("SELECT g.id, g.name, COUNT(s.id) as count FROM GroupEntity g LEFT JOIN StudentEntity s ON g.id=s.groupId GROUP BY g.id")
    fun getGroupsWithInfo(): Flow<List<GroupFullData>>


}
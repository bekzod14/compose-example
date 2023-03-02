package uz.gita.composeexample2.data.source.room.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.source.room.entities.StudentEntity

@Dao
interface StudentDao : BaseDao<StudentEntity> {

    @Query("SELECT * FROM StudentEntity")
    fun getStudents(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM StudentEntity WHERE groupId =:groupId")
    fun getStudentsByGroup(groupId: Long): Flow<List<StudentEntity>>

}
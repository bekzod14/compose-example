package uz.gita.composeexample2.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.models.GroupFullData
import uz.gita.composeexample2.data.source.room.entities.GroupEntity
import uz.gita.composeexample2.data.source.room.entities.StudentEntity

interface StudentRepository {

    suspend fun addStudent(data: StudentEntity)

    suspend fun updateStudent(data: StudentEntity)

    suspend fun deleteStudent(data: StudentEntity)

    fun getStudents(groupId: Long): Flow<List<StudentEntity>>
}
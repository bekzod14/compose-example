package uz.gita.composeexample2.data.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.composeexample2.data.repository.StudentRepository
import uz.gita.composeexample2.data.source.room.dao.StudentDao
import uz.gita.composeexample2.data.source.room.entities.StudentEntity
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : StudentRepository {

    override suspend fun addStudent(data: StudentEntity) = studentDao.add(data)

    override suspend fun updateStudent(data: StudentEntity) = studentDao.update(data)

    override suspend fun deleteStudent(data: StudentEntity) = studentDao.delete(data)

    override fun getStudents(groupId: Long): Flow<List<StudentEntity>> =
        studentDao.getStudentsByGroup(groupId)
}
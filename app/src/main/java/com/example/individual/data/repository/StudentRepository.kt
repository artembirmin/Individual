package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val studentDao = DatabaseProvider.get().getStudentDao()
    private val groupRepository = GroupRepository.getInstance()

    fun observeStudents(studentId: Long): Flow<List<Student>> {
        return studentDao.getAll(studentId)
    }

    suspend fun refreshStudents() {
        val students = individualApi.getStudents()
        studentDao.insertAll(students)
    }

    suspend fun add(newStudent: Student) {
        val student =
            individualApi.addStudent(newStudent)
        studentDao.insert(student)
        groupRepository.updateGroups()
    }

    suspend fun update(updatedStudent: Student) {
        val student =
            individualApi.updateStudent(
                updatedStudent.id,
                updatedStudent
            )
        studentDao.insert(student)
        groupRepository.updateGroups()
    }

    suspend fun delete(student: Student) {
        individualApi.deleteStudent(student.id)
        studentDao.delete(student)
        groupRepository.updateGroups()
    }

    suspend fun getStudentById(id: Long): Student {
        return studentDao.getById(id)
    }

    companion object {
        private var INSTANCE: StudentRepository? = null
        fun getInstance(): StudentRepository {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
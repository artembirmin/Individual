package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Department
import kotlinx.coroutines.flow.Flow

class DepartmentRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val departmentDao = DatabaseProvider.get().getDepartmentDao()

    fun observeDepartments(facultyId: Long): Flow<List<Department>> {
        return departmentDao.getDepartments(facultyId)
    }

    suspend fun refreshDepartments() {
        val faculties = individualApi.getDepartments().map { it }
        departmentDao.insertAll(faculties)
    }

    suspend fun add(department: Department) {
        val facultyFromServer =
            individualApi.addDepartment(department)
        departmentDao.insert(facultyFromServer)
    }

    suspend fun update(department: Department) {
        val facultyFromServer =
            individualApi.updateDepartment(department.id, department)

        departmentDao.insert(facultyFromServer)
    }

    suspend fun delete(department: Department) {
        individualApi.deleteDepartment(department.id)
        departmentDao.delete(department)
    }

    suspend fun getDepartmentById(id: Long): Department {
        return departmentDao.getById(id)
    }

    companion object {
        private var INSTANCE: DepartmentRepository? = null
        fun getInstance(): DepartmentRepository {
            if (INSTANCE == null) {
                INSTANCE = DepartmentRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
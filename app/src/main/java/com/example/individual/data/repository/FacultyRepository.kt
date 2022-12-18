package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Faculty
import kotlinx.coroutines.flow.Flow

class FacultyRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val facultyDao = DatabaseProvider.get().getFacultyDao()

    fun observeFaculties(): Flow<List<Faculty>> {
        return facultyDao.getFaculties()
    }

    suspend fun getFacultyById(id: Long): Faculty {
        return facultyDao.getFacultyById(id)
    }

    suspend fun refreshFaculties() {
        val faculties = individualApi.getFaculties()
        facultyDao.insertAll(faculties)
    }

    suspend fun add(faculty: Faculty) {
        val facultyFromServer = individualApi.addFaculty(faculty)
        facultyDao.insert(facultyFromServer)
    }

    suspend fun update(faculty: Faculty) {
        val facultyFromServer = individualApi.updateFaculty(faculty.id, faculty)
        facultyDao.insert(facultyFromServer)
    }

    suspend fun delete(faculty: Faculty) {
        individualApi.deleteFaculty(faculty.id)
        facultyDao.deleteFaculty(faculty)
    }

    companion object {
        private var INSTANCE: FacultyRepository? = null
        fun getInstance(): FacultyRepository {
            if (INSTANCE == null) {
                INSTANCE = FacultyRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
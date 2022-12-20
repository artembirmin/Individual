package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Faculty
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий для работы с факультетами. Ходит в базу. Ходит на бэк.
 */
class FacultyRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val facultyDao = DatabaseProvider.get().getFacultyDao()

    // Делается подписка на изменения в базе. Ручками каждый раз запрашивать не надо
    // https://medium.com/androiddevelopers/room-flow-273acffe5b57
    fun observeFaculties(): Flow<List<Faculty>> {
        return facultyDao.getFaculties()
    }

    // suspend - приостанавливаемая функция. Деает возможность асинхронной работы
    // https://startandroid.ru/ru/courses/kotlin/29-course/kotlin/596-urok-3-korutiny-suspend-function.html
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
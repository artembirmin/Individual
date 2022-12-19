package com.example.individual.data.network

import com.example.individual.model.Department
import com.example.individual.model.Faculty
import kotlinx.coroutines.delay

class IndividualApiMock {
    suspend fun getFaculties(): List<Faculty> {
        delay(1000)
        return listOf()
    }

    suspend fun addFaculty(faculty: Faculty): Faculty {
        delay(1000)
        return faculty
    }

    suspend fun updateFaculty(faculty: Faculty): Faculty {
        delay(1000)
        return faculty
    }

    suspend fun getDepartments(): List<Department> {
        delay(1000)
        return listOf()
    }

    fun addDepartment(faculty: Department): Department {
        return faculty
    }

    fun updateDepartment(faculty: Department): Department {
        return faculty
    }

    suspend fun deleteFaculty(faculty: Faculty) {
        delay(1000)
    }

    suspend fun deleteDepartment(department: Department) {
        delay(1000)
    }
}
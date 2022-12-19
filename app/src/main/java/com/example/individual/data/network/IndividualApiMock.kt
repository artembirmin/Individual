package com.example.individual.data.network

import com.example.individual.model.Faculty
import com.example.individual.model.Group
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

    suspend fun getGroups(): List<Group> {
        delay(1000)
        return listOf()
    }

    fun addGroup(faculty: Group): Group {
        return faculty
    }

    fun updateGroup(faculty: Group): Group {
        return faculty
    }

    suspend fun deleteFaculty(faculty: Faculty) {
        delay(1000)
    }

    suspend fun deleteGroup(group: Group) {
        delay(1000)
    }
}
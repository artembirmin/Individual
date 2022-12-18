package com.example.individual.data.network

import com.example.individual.model.DepartmentFull
import com.example.individual.model.Faculty
import kotlinx.coroutines.delay
import org.joda.time.DateTime

class IndividualApiMock {
    suspend fun getFaculties(): List<Faculty> {
        delay(1000)
        return listOf(
            Faculty("1", 1),
            Faculty("2", 2),
            Faculty("3", 3),
        )
    }

    suspend fun addFaculty(faculty: Faculty): Faculty {
        delay(1000)
        return faculty
    }

    suspend fun updateFaculty(faculty: Faculty): Faculty {
        delay(1000)
        return faculty
    }

    suspend fun getDepartments(): List<DepartmentFull> {
        delay(1000)
        return listOf(
            DepartmentFull(
                id = 1,
                facultyId = 1,
                onboardNumber = "2",
                flightNumber = "322",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            DepartmentFull(
                id = 2,
                facultyId = 1,
                onboardNumber = "3",
                flightNumber = "133",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            DepartmentFull(
                id = 3,
                facultyId = 1,
                onboardNumber = "1",
                flightNumber = "211",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            DepartmentFull(
                id = 4,
                facultyId = 2,
                onboardNumber = "2221",
                flightNumber = "12345",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
        )
    }

    fun addDepartment(faculty: DepartmentFull): DepartmentFull {
        return faculty
    }

    fun updateDepartment(faculty: DepartmentFull): DepartmentFull {
        return faculty
    }

    suspend fun deleteFaculty(faculty: Faculty) {
        delay(1000)
    }

    suspend fun deleteDepartment(department: DepartmentFull) {
        delay(1000)
    }
}
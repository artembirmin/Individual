package com.example.individual.data.net

import com.example.individual.model.Airline
import com.example.individual.model.PlaneFull
import kotlinx.coroutines.delay
import org.joda.time.DateTime

class IndividualApiMock {
    suspend fun getAirlines(): List<Airline> {
        delay(1000)
        return listOf(
            Airline("1", 1),
            Airline("2", 2),
            Airline("3", 3),
        )
    }

    suspend fun addAirline(airline: Airline): Airline {
        delay(1000)
        return airline
    }

    suspend fun updateAirline(airline: Airline): Airline {
        delay(1000)
        return airline
    }

    suspend fun getPlanes(): List<PlaneFull> {
        delay(1000)
        return listOf(
            PlaneFull(
                id = 1,
                airlineId = 1,
                onboardNumber = "2",
                flightNumber = "322",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            PlaneFull(
                id = 2,
                airlineId = 1,
                onboardNumber = "3",
                flightNumber = "133",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            PlaneFull(
                id = 3,
                airlineId = 1,
                onboardNumber = "1",
                flightNumber = "211",
                flightFrom = "we",
                flightTo = "sdf",
                boardingDateTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
            ),
            PlaneFull(
                id = 4,
                airlineId = 2,
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

    fun addPlane(airline: PlaneFull): PlaneFull {
        return airline
    }

    fun updatePlane(airline: PlaneFull): PlaneFull {
        return airline
    }

    suspend fun deleteAirline(airline: Airline) {
        delay(1000)
    }

    suspend fun deletePlane(plane: PlaneFull) {
        delay(1000)
    }
}
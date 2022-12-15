package com.example.individual.data.network

import com.example.individual.model.Airline
import com.example.individual.model.PlaneFull
import kotlinx.coroutines.delay
import org.joda.time.DateTime

class IndividualApiMock {
    suspend fun getAirlines(): List<Airline> {
        delay(2000)
        return listOf(
            Airline("5", "1"),
            Airline("2", "2"),
            Airline("3", "3"),
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

    fun getPlanes(): List<PlaneFull> {
        return listOf(
            PlaneFull(
                id = "1",
                airlineId = "1",
                onboardNumber = "111",
                flightNumber = "12345",
                flightFrom = "we",
                flightTo = "sdf",
                boardingTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
                seatsCount = 1234.0,
                maxSpeedKmh = 123.0
            ),
            PlaneFull(
                id = "2",
                airlineId = "1",
                onboardNumber = "112",
                flightNumber = "12345",
                flightFrom = "we",
                flightTo = "sdf",
                boardingTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
                seatsCount = 1234.0,
                maxSpeedKmh = 123.0
            ),
            PlaneFull(
                id = "3",
                airlineId = "2",
                onboardNumber = "2221",
                flightNumber = "12345",
                flightFrom = "we",
                flightTo = "sdf",
                boardingTime = DateTime.now(),
                gate = "123",
                firstPilotName = "Bdfy tyt",
                secondPilotName = "ddwd wewe",
                seatsCount = 1234.0,
                maxSpeedKmh = 123.0
            ),
        )
    }

    fun addPlane(airline: PlaneFull): PlaneFull {
        return airline
    }

    fun updatePlane(airline: PlaneFull): PlaneFull {
        return airline
    }
}
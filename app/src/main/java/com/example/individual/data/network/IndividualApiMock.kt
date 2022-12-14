package com.example.individual.data.network

import com.example.individual.model.Airline
import kotlinx.coroutines.delay

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
}
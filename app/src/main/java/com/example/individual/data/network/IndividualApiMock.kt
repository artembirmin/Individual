package com.example.individual.data.network

import com.example.individual.model.Airline
import kotlinx.coroutines.delay

class IndividualApiMock {
    suspend fun getAirlines(): List<Airline> {
        delay(1000)
        return listOf(
            Airline("1"),
            Airline("2"),
            Airline("3"),
        )
    }
}
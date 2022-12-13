package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.IndividualApiMock
import com.example.individual.model.Airline
import kotlinx.coroutines.flow.Flow

class AirlineRepository {

    private val individualApi = IndividualApiMock()
    private val airlineDao = DatabaseProvider.get().getAirlineDao()

    suspend fun observeAirlines(): Flow<List<Airline>> {
        val airlines = individualApi.getAirlines()
        airlineDao.insertAll(airlines)
        return airlineDao.getAirlines()
    }

    companion object {
        private var INSTANCE: AirlineRepository? = null
        fun getInstance(): AirlineRepository {
            if (INSTANCE == null) {
                INSTANCE = AirlineRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
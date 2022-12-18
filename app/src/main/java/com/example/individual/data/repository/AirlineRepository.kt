package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Airline
import kotlinx.coroutines.flow.Flow

class AirlineRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val airlineDao = DatabaseProvider.get().getAirlineDao()

    fun observeAirlines(): Flow<List<Airline>> {
        return airlineDao.getAirlines()
    }

    suspend fun getAirlineById(id: Long): Airline {
        return airlineDao.getAirlineById(id)
    }

    suspend fun refreshAirlines() {
        val airlines = individualApi.getAirlines()
        airlineDao.insertAll(airlines)
    }

    suspend fun add(airline: Airline) {
        val airlineFromServer = individualApi.addAirline(airline)
        airlineDao.insert(airlineFromServer)
    }

    suspend fun update(airline: Airline) {
        val airlineFromServer = individualApi.updateAirline(airline.id, airline)
        airlineDao.insert(airlineFromServer)
    }

    suspend fun delete(airline: Airline) {
        individualApi.deleteAirline(airline.id)
        airlineDao.deleteAirline(airline)
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
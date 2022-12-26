package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.GasStation
import kotlinx.coroutines.flow.Flow

class GasStationRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val gasStationDao = DatabaseProvider.get().getGasStationDao()

    fun observeGasStations(): Flow<List<GasStation>> {
        return gasStationDao.getGasStations()
    }

    suspend fun getGasStationById(id: Long): GasStation {
        return gasStationDao.getGasStationById(id)
    }

    suspend fun refreshGasStations() {
        val gasStations = individualApi.getGasStations()
        gasStationDao.deleteAll()
        gasStationDao.insertAll(gasStations)
    }

    suspend fun add(gasStation: GasStation) {
        val gasStationFromServer = individualApi.addGasStation(gasStation)
        gasStationDao.insert(gasStationFromServer)
    }

    suspend fun update(gasStation: GasStation) {
        val gasStationFromServer = individualApi.updateGasStation(gasStation.id, gasStation)
        gasStationDao.insert(gasStationFromServer)
    }

    suspend fun delete(gasStation: GasStation) {
        individualApi.deleteGasStation(gasStation.id)
        gasStationDao.deleteGasStation(gasStation)
    }

    companion object {
        private var INSTANCE: GasStationRepository? = null
        fun getInstance(): GasStationRepository {
            if (INSTANCE == null) {
                INSTANCE = GasStationRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
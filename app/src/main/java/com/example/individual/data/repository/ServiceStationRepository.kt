package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.ServiceStation
import kotlinx.coroutines.flow.Flow

class ServiceStationRepository {
    private val individualApi = NetworkProvider.get().api
    private val serviceStationDao = DatabaseProvider.get().getServiceStationDao()

    fun observeServiceStations(): Flow<List<ServiceStation>> {
        return serviceStationDao.getServiceStations()
    }

    suspend fun getServiceStationById(id: Long): ServiceStation {
        return serviceStationDao.getServiceStationById(id)
    }

    suspend fun refreshServiceStations() {
        val serviceStations = individualApi.getServiceStations()
        serviceStationDao.deleteAll()
        serviceStationDao.insertAll(serviceStations)
    }

    suspend fun add(serviceStation: ServiceStation) {
        val serviceStationFromServer = individualApi.addServiceStation(serviceStation)
        serviceStationDao.insert(serviceStationFromServer)
    }

    suspend fun update(serviceStation: ServiceStation) {
        val serviceStationFromServer =
            individualApi.updateServiceStation(serviceStation.id, serviceStation)
        serviceStationDao.insert(serviceStationFromServer)
    }

    suspend fun delete(serviceStation: ServiceStation) {
        individualApi.deleteServiceStation(serviceStation.id)
        serviceStationDao.deleteServiceStation(serviceStation)
    }

    companion object {
        private var INSTANCE: ServiceStationRepository? = null
        fun getInstance(): ServiceStationRepository {
            if (INSTANCE == null) {
                INSTANCE = ServiceStationRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
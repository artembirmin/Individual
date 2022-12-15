package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.IndividualApiMock
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

class PlaneRepository {

    private val individualApi = IndividualApiMock()
    private val airlineDao = DatabaseProvider.get().getPlaneDao()

    fun observePlanes(airlineId: String): Flow<List<PlaneShort>> {
        return airlineDao.getPlanes(airlineId)
    }

    suspend fun refreshPlanes() {
        val airlines = individualApi.getPlanes()
        airlineDao.insertAll(airlines)
    }

    suspend fun add(airline: PlaneFull) {
        val airlineFromServer = individualApi.addPlane(airline)
        airlineDao.insert(airlineFromServer)
    }

    suspend fun update(airline: PlaneFull) {
        val airlineFromServer = individualApi.updatePlane(airline)
        airlineDao.insert(airlineFromServer)
    }

    companion object {
        private var INSTANCE: PlaneRepository? = null
        fun getInstance(): PlaneRepository {
            if (INSTANCE == null) {
                INSTANCE = PlaneRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
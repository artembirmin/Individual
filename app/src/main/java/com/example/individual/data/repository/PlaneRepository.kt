package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.IndividualApiMock
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

class PlaneRepository {

    private val individualApi = IndividualApiMock()
    private val planeDao = DatabaseProvider.get().getPlaneDao()

    fun observePlanes(airlineId: String): Flow<List<PlaneShort>> {
        return planeDao.getPlanes(airlineId)
    }

    suspend fun refreshPlanes() {
        val airlines = individualApi.getPlanes()
        planeDao.insertAll(airlines)
    }

    suspend fun add(airline: PlaneFull) {
        val airlineFromServer = individualApi.addPlane(airline)
        planeDao.insert(airlineFromServer)
    }

    suspend fun update(airline: PlaneFull) {
        val airlineFromServer = individualApi.updatePlane(airline)
        planeDao.insert(airlineFromServer)
    }

    suspend fun delete(plane: PlaneFull) {
        individualApi.deletePlane(plane)
        planeDao.delete(plane)
    }

    suspend fun getPlaneById(id: String): PlaneFull {
        return planeDao.getById(id)
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
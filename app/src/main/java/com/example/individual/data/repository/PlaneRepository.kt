package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

class PlaneRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val planeDao = DatabaseProvider.get().getPlaneDao()

    fun observePlanes(airlineId: Long): Flow<List<PlaneShort>> {
        return planeDao.getPlanes(airlineId)
    }

    suspend fun refreshPlanes() {
        val airlines = individualApi.getPlanes().map { it.toPlaneFull() }
        planeDao.insertAll(airlines)
    }

    suspend fun add(plane: PlaneFull) {
        val airlineFromServer = individualApi.addPlane(plane.toServerModel()).toPlaneFull()
        planeDao.insert(airlineFromServer)
    }

    suspend fun update(plane: PlaneFull) {
        val airlineFromServer =
            individualApi.updatePlane(plane.id, plane.toServerModel()).toPlaneFull()
        planeDao.insert(airlineFromServer)
    }

    suspend fun delete(plane: PlaneFull) {
        individualApi.deletePlane(plane.id)
        planeDao.delete(plane)
    }

    suspend fun getPlaneById(id: Long): PlaneFull {
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
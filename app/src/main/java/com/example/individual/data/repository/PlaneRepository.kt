package com.example.individual.data.repository

import com.example.individual.data.db.DatabaseProvider
import com.example.individual.data.net.NetworkProvider
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

class PlaneRepository {

    private val individualApi = NetworkProvider.get().api
    private val planeDao = DatabaseProvider.get().getPlaneDao()

    fun observePlanes(airlineId: Long): Flow<List<PlaneShort>> {
        return planeDao.getPlanes(airlineId)
    }

    suspend fun refreshPlanes() {
        val airlines = individualApi.getPlanes().map { it.toPlaneFull() }
        planeDao.insertAirlines(airlines)
    }

    suspend fun add(plane: PlaneFull) {
        val airlineFromServer = individualApi.addPlane(plane.toServerModel()).toPlaneFull()
        planeDao.insertAirline(airlineFromServer)
    }

    suspend fun update(plane: PlaneFull) {
        val airlineFromServer =
            individualApi.updatePlane(plane.id, plane.toServerModel()).toPlaneFull()
        planeDao.insertAirline(airlineFromServer)
    }

    suspend fun deleteById(planeId: Long) {
        // Если запрос возвращается с ошибкой, то корутина не умирает
        // Как выводить сообщение об ошибке
        individualApi.deletePlane(planeId)
        planeDao.deleteById(planeId)

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
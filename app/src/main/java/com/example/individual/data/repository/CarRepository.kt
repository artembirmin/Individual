package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.CarFull
import com.example.individual.model.CarShort
import kotlinx.coroutines.flow.Flow

class CarRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val carDao = DatabaseProvider.get().getCarDao()

    fun observeCars(gasStationId: Long): Flow<List<CarShort>> {
        return carDao.getCars(gasStationId)
    }

    suspend fun refreshCars() {
        val gasStations = individualApi.getCars().map { it.toCarFull() }
        carDao.insertAll(gasStations)
    }

    suspend fun add(car: CarFull) {
        val gasStationFromServer = individualApi.addCar(car.toServerModel()).toCarFull()
        carDao.insert(gasStationFromServer)
    }

    suspend fun update(car: CarFull) {
        val gasStationFromServer =
            individualApi.updateCar(car.id, car.toServerModel()).toCarFull()
        carDao.insert(gasStationFromServer)
    }

    suspend fun delete(plane: CarFull) {
        individualApi.deleteCar(plane.id)
        carDao.delete(plane)
    }

    suspend fun getCarById(id: Long): CarFull {
        return carDao.getById(id)
    }

    companion object {
        private var INSTANCE: CarRepository? = null
        fun getInstance(): CarRepository {
            if (INSTANCE == null) {
                INSTANCE = CarRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
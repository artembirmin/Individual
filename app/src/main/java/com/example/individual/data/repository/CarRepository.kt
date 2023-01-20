package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.CarFull
import com.example.individual.model.CarShort
import kotlinx.coroutines.flow.Flow
import org.joda.time.DateTime

class CarRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val carDao = DatabaseProvider.get().getCarDao()

    fun observeCars(gasStationId: Long, date: DateTime): Flow<List<CarShort>> {
        return carDao.getCars(gasStationId, date)
    }

    suspend fun refreshCars() {
        val gasStations = individualApi.getCars().map { it.toCarFull() }
        carDao.deleteAll()
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

    suspend fun delete(carFull: CarFull) {
        individualApi.deleteCar(carFull.id)
        carDao.delete(carFull)
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
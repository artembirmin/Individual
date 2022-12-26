package com.example.individual.data.network

import com.example.individual.model.CarServerModel
import com.example.individual.model.GasStation
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("gas_stations")
    suspend fun getGasStations(): List<GasStation>

    @POST("gas_station")
    suspend fun addGasStation(@Body gasStation: GasStation): GasStation

    @PATCH("gas_stations/{id}")
    suspend fun updateGasStation(@Path("id") id: Long, @Body gasStation: GasStation): GasStation

    @DELETE("gas_stations/{id}")
    suspend fun deleteGasStation(@Path("id") id: Long): Response<Unit>

    @GET("cars")
    suspend fun getCars(): List<CarServerModel>

    @POST("car")
    suspend fun addCar(@Body car: CarServerModel): CarServerModel

    @PATCH("cars/{id}")
    suspend fun updateCar(@Path("id") id: Long, @Body car: CarServerModel): CarServerModel

    @DELETE("cars/{id}")
    suspend fun deleteCar(@Path("id") id: Long): Response<Unit>
}
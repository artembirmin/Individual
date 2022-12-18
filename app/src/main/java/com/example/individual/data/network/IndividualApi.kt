package com.example.individual.data.network

import com.example.individual.model.Airline
import com.example.individual.model.PlaneServerModel
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("airlines")
    suspend fun getAirlines(): List<Airline>

    @POST("airline")
    suspend fun addAirline(@Body airline: Airline): Airline

    @PATCH("airlines/{id}")
    suspend fun updateAirline(@Path("id") id: Long, @Body airline: Airline): Airline

    @DELETE("airlines/{id}")
    suspend fun deleteAirline(@Path("id") id: Long): Response<Unit>

    @GET("planes")
    suspend fun getPlanes(): List<PlaneServerModel>

    @POST("plane")
    suspend fun addPlane(@Body plane: PlaneServerModel): PlaneServerModel

    @PATCH("planes/{id}")
    suspend fun updatePlane(@Path("id") id: Long, @Body plane: PlaneServerModel): PlaneServerModel

    @DELETE("planes/{id}")
    suspend fun deletePlane(@Path("id") id: Long): Response<Unit>
}
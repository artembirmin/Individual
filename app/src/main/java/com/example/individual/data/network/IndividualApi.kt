package com.example.individual.data.network

import com.example.individual.model.Airline
import retrofit2.http.GET


interface IndividualApi {

    @GET("models")
    suspend fun getModels(): Airline

}
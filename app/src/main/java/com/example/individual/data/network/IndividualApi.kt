package com.example.individual.data.network

import com.example.individual.model.Client
import com.example.individual.model.OrderServerModel
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("clients")
    suspend fun getClients(): List<Client>

    @POST("client")
    suspend fun addClient(@Body client: Client): Client

    @PATCH("clients/{id}")
    suspend fun updateClient(@Path("id") id: Long, @Body client: Client): Client

    @DELETE("clients/{id}")
    suspend fun deleteClient(@Path("id") id: Long): Response<Unit>

    @GET("orders")
    suspend fun getOrders(): List<OrderServerModel>

    @POST("order")
    suspend fun addOrder(@Body order: OrderServerModel): OrderServerModel

    @PATCH("orders/{id}")
    suspend fun updateOrder(@Path("id") id: Long, @Body order: OrderServerModel): OrderServerModel

    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") id: Long): Response<Unit>
}
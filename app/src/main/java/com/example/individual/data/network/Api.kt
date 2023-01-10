package com.example.individual.data.network

import com.example.individual.model.ServiceStation
import com.example.individual.model.WorkOrderServerModel
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @GET("gas_stations")
    suspend fun getServiceStations(): List<ServiceStation>

    @POST("gas_station")
    suspend fun addServiceStation(@Body serviceStation: ServiceStation): ServiceStation

    @PATCH("gas_stations/{id}")
    suspend fun updateServiceStation(
        @Path("id") id: Long,
        @Body serviceStation: ServiceStation
    ): ServiceStation

    @DELETE("gas_stations/{id}")
    suspend fun deleteServiceStation(@Path("id") id: Long): Response<Unit>

    @GET("workOrders")
    suspend fun getWorkOrders(): List<WorkOrderServerModel>

    @POST("workOrder")
    suspend fun addWorkOrder(@Body workOrder: WorkOrderServerModel): WorkOrderServerModel

    @PATCH("workOrders/{id}")
    suspend fun updateWorkOrder(
        @Path("id") id: Long,
        @Body workOrder: WorkOrderServerModel
    ): WorkOrderServerModel

    @DELETE("workOrders/{id}")
    suspend fun deleteWorkOrder(@Path("id") id: Long): Response<Unit>
}
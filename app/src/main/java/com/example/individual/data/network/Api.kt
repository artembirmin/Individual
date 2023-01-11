package com.example.individual.data.network

import com.example.individual.model.ServiceStation
import com.example.individual.model.WorkOrderServerModel
import retrofit2.Response
import retrofit2.http.*


interface Api {

    @GET("service_stations")
    suspend fun getServiceStations(): List<ServiceStation>

    @POST("service_station")
    suspend fun addServiceStation(@Body serviceStation: ServiceStation): ServiceStation

    @PATCH("service_stations/{id}")
    suspend fun updateServiceStation(
        @Path("id") id: Long,
        @Body serviceStation: ServiceStation
    ): ServiceStation

    @DELETE("service_stations/{id}")
    suspend fun deleteServiceStation(@Path("id") id: Long): Response<Unit>

    @GET("work_orders")
    suspend fun getWorkOrders(): List<WorkOrderServerModel>

    @POST("work_order")
    suspend fun addWorkOrder(@Body workOrder: WorkOrderServerModel): WorkOrderServerModel

    @PATCH("work_orders/{id}")
    suspend fun updateWorkOrder(
        @Path("id") id: Long,
        @Body work_order: WorkOrderServerModel
    ): WorkOrderServerModel

    @DELETE("work_orders/{id}")
    suspend fun deleteWorkOrder(@Path("id") id: Long): Response<Unit>
}
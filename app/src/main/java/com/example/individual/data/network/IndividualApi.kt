package com.example.individual.data.network

import com.example.individual.model.DepartmentServerModel
import com.example.individual.model.Faculty
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("faculties1")
    suspend fun getFaculties(): List<Faculty>

    @POST("faculty1")
    suspend fun addFaculty(@Body faculty: Faculty): Faculty

    @PATCH("faculties1/{id}")
    suspend fun updateFaculty(@Path("id") id: Long, @Body faculty: Faculty): Faculty

    @DELETE("faculties1/{id}")
    suspend fun deleteFaculty(@Path("id") id: Long): Response<Unit>

    @GET("departments")
    suspend fun getDepartments(): List<DepartmentServerModel>

    @POST("department")
    suspend fun addDepartment(@Body department: DepartmentServerModel): DepartmentServerModel

    @PATCH("departments/{id}")
    suspend fun updateDepartment(
        @Path("id") id: Long,
        @Body department: DepartmentServerModel
    ): DepartmentServerModel

    @DELETE("departments/{id}")
    suspend fun deleteDepartment(@Path("id") id: Long): Response<Unit>
}
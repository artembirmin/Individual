package com.example.individual.data.network

import com.example.individual.model.Faculty
import com.example.individual.model.Group
import com.example.individual.model.Student
import retrofit2.Response
import retrofit2.http.*


interface IndividualApi {

    @GET("faculties2")
    suspend fun getFaculties(): List<Faculty>

    @POST("faculty2")
    suspend fun addFaculty(@Body faculty: Faculty): Faculty

    @PATCH("faculties2/{id}")
    suspend fun updateFaculty(@Path("id") id: Long, @Body faculty: Faculty): Faculty

    @DELETE("faculties2/{id}")
    suspend fun deleteFaculty(@Path("id") id: Long): Response<Unit>

    @GET("groups")
    suspend fun getGroups(): List<Group>

    @POST("group")
    suspend fun addGroup(@Body group: Group): Group

    @PATCH("groups/{id}")
    suspend fun updateGroup(
        @Path("id") id: Long,
        @Body group: Group
    ): Group

    @DELETE("groups/{id}")
    suspend fun deleteGroup(@Path("id") id: Long): Response<Unit>

    @GET("students")
    suspend fun getStudents(): List<Student>

    @POST("student")
    suspend fun addStudent(@Body student: Student): Student

    @PATCH("students/{id}")
    suspend fun updateStudent(
        @Path("id") id: Long,
        @Body student: Student
    ): Student

    @DELETE("students/{id}")
    suspend fun deleteStudent(@Path("id") id: Long): Response<Unit>
}
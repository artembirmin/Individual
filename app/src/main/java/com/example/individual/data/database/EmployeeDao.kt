package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  departmentId = (:departmentId)")
    fun getAll(departmentId: Long): Flow<List<Employee>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Employee

    @Update
    suspend fun update(employee: Employee)

    @Update
    suspend fun updateAll(ist: List<Employee>)

    @Delete
    suspend fun delete(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Employee>)

    companion object {
        private const val TABLE_NAME = Employee.TABLE_NAME
    }
}
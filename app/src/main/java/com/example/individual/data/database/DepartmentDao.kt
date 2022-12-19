package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  facultyId = (:facultyId)")
    fun getDepartments(facultyId: Long): Flow<List<Department>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Department

    @Update
    suspend fun update(department: Department)

    @Update
    suspend fun updateAll(ist: List<Department>)

    @Delete
    suspend fun delete(department: Department)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(department: Department)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Department>)

    companion object {
        private const val TABLE_NAME = Department.TABLE_NAME
    }
}
package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.DepartmentFull
import com.example.individual.model.DepartmentShort
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  facultyId = (:facultyId)")
    fun getDepartments(facultyId: Long): Flow<List<DepartmentShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): DepartmentFull

    @Update
    suspend fun update(departmentFull: DepartmentFull)

    @Update
    suspend fun updateAll(ist: List<DepartmentFull>)

    @Delete
    suspend fun delete(departmentFull: DepartmentFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(departmentFull: DepartmentFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<DepartmentFull>)

    companion object {
        private const val TABLE_NAME = DepartmentFull.TABLE_NAME
    }
}
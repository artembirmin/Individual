package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Faculty
import kotlinx.coroutines.flow.Flow

@Dao
interface FacultyDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getFaculties(): Flow<List<Faculty>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getFacultyById(id: Long): Faculty

    @Update
    suspend fun updateFaculty(faculty: Faculty)

    @Delete
    suspend fun deleteFaculty(faculty: Faculty)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(faculty: Faculty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Faculty>)

    companion object {
        private const val TABLE_NAME = Faculty.TABLE_NAME
    }
}
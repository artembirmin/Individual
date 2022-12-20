package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Faculty
import kotlinx.coroutines.flow.Flow

/**
 * Что это такое:
 * https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/529-urok-5-room-osnovy.html#:~:text=Entity%20%D0%B1%D0%BE%D0%BB%D0%B5%D0%B5%20%D0%BF%D0%BE%D0%B4%D1%80%D0%BE%D0%B1%D0%BD%D0%BE.-,Dao,-%D0%92%20%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%B5%20Dao
 */
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

    // Конфликт происходит при совпадении id вставляемого элемента и элемента в базе.
    // Когда это происходит, будет замена элемента
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(faculty: Faculty)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Faculty>)

    companion object {
        private const val TABLE_NAME = Faculty.TABLE_NAME
    }
}
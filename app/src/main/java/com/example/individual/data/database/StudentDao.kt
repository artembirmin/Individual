package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface
StudentDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  groupId = (:groupId)")
    fun getAll(groupId: Long): Flow<List<Student>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Student

    @Update
    suspend fun update(student: Student)

    @Update
    suspend fun updateAll(ist: List<Student>)

    @Delete
    suspend fun delete(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Student>)

    companion object {
        private const val TABLE_NAME = Student.TABLE_NAME
    }
}
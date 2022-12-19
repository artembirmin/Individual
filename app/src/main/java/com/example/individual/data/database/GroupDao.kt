package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  facultyId = (:facultyId)")
    fun getGroups(facultyId: Long): Flow<List<Group>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): Group

    @Update
    suspend fun update(group: Group)

    @Update
    suspend fun updateAll(list: List<Group>)

    @Delete
    suspend fun delete(group: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Group>)

    companion object {
        private const val TABLE_NAME = Group.TABLE_NAME
    }
}
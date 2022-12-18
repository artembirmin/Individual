package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaneDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  airlineId = (:airlineId)")
    fun getPlanes(airlineId: Long): Flow<List<PlaneShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): PlaneFull

    @Update
    suspend fun update(planeFull: PlaneFull)

    @Update
    suspend fun updateAll(ist: List<PlaneFull>)

    @Delete
    suspend fun delete(planeFull: PlaneFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(planeFull: PlaneFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PlaneFull>)

    companion object {
        private const val TABLE_NAME = PlaneFull.TABLE_NAME
    }
}
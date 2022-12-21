package com.example.individual.data.db

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
    suspend fun updatePlane(planeFull: PlaneFull)

    @Update
    suspend fun updatePlanes(list: List<PlaneFull>)

    @Delete
    suspend fun delete(planeFull: PlaneFull)

    @Query("DELETE FROM $TABLE_NAME WHERE  id = (:planeId)")
    suspend fun deleteById(planeId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirline(planeFull: PlaneFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirlines(list: List<PlaneFull>)

    companion object {
        private const val TABLE_NAME = PlaneFull.TABLE_NAME
    }
}
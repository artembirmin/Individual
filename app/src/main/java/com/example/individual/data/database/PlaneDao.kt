package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.PlaneFull
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaneDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  airlineId = (:airlineId)")
    fun getPlanes(airlineId: String): Flow<List<PlaneShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getPlaneById(id: String): PlaneFull

    @Update
    suspend fun updatePlane(planeFull: PlaneFull)

    @Delete
    suspend fun deletePlane(planeFull: PlaneFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(planeFull: PlaneFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PlaneFull>)

    companion object {
        private const val TABLE_NAME = PlaneFull.TABLE_NAME
    }
}
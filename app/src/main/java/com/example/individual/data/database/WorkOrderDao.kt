package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.WorkOrderFull
import com.example.individual.model.WorkOrderShort
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkOrderDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  serviceStationId = (:serviceStationId)")
    fun getWorkOrders(serviceStationId: Long): Flow<List<WorkOrderShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): WorkOrderFull

    @Update
    suspend fun update(workOrderFull: WorkOrderFull)

    @Update
    suspend fun updateAll(ist: List<WorkOrderFull>)

    @Delete
    suspend fun delete(workOrderFull: WorkOrderFull)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workOrderFull: WorkOrderFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<WorkOrderFull>)

    companion object {
        private const val TABLE_NAME = WorkOrderFull.TABLE_NAME
    }
}
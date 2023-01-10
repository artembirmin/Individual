package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.ServiceStation
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceStationDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getServiceStations(): Flow<List<ServiceStation>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getServiceStationById(id: Long): ServiceStation

    @Update
    suspend fun updateServiceStation(serviceStation: ServiceStation)

    @Delete
    suspend fun deleteServiceStation(serviceStation: ServiceStation)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serviceStation: ServiceStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ServiceStation>)

    companion object {
        private const val TABLE_NAME = ServiceStation.TABLE_NAME
    }
}
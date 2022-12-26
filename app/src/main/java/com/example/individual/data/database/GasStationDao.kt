package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.GasStation
import kotlinx.coroutines.flow.Flow

@Dao
interface GasStationDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getGasStations(): Flow<List<GasStation>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getGasStationById(id: Long): GasStation

    @Update
    suspend fun updateGasStation(gasStation: GasStation)

    @Delete
    suspend fun deleteGasStation(gasStation: GasStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gasStation: GasStation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GasStation>)

    companion object {
        private const val TABLE_NAME = GasStation.TABLE_NAME
    }
}
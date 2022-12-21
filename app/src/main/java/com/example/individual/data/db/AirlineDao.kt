package com.example.individual.data.db

import androidx.room.*
import com.example.individual.model.Airline
import kotlinx.coroutines.flow.Flow

@Dao
interface AirlineDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAirlines(): Flow<List<Airline>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getAirlineById(id: Long): Airline

    @Update
    suspend fun updateAirline(airline: Airline)

    @Delete
    suspend fun deleteAirline(airline: Airline)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirline(airline: Airline)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirlines(list: List<Airline>)

    companion object {
        private const val TABLE_NAME = Airline.TABLE_NAME
    }
}
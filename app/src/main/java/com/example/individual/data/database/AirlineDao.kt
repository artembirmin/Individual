package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Airline
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface AirlineDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAirlines(): Flow<List<Airline>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    fun getAirlineById(id: UUID): Flow<Airline>

    @Update
    suspend fun updateAirline(airline: Airline)

    @Insert
    suspend fun addAirline(airline: Airline)

    @Delete
    suspend fun deleteAirline(airline: Airline)

    companion object {
        private const val TABLE_NAME = Airline.TABLE_NAME
    }
}
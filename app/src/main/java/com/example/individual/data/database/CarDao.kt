package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.CarFull
import com.example.individual.model.CarShort
import kotlinx.coroutines.flow.Flow
import org.joda.time.DateTime

@Dao
interface CarDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  gasStationId = (:gasStationId) AND fuelingDate = :date")
    fun getCars(gasStationId: Long, date: DateTime): Flow<List<CarShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): CarFull

    @Update
    suspend fun update(carFull: CarFull)

    @Update
    suspend fun updateAll(ist: List<CarFull>)

    @Delete
    suspend fun delete(carFull: CarFull)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(carFull: CarFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CarFull>)

    companion object {
        private const val TABLE_NAME = CarFull.TABLE_NAME
    }
}
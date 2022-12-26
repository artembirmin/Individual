package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.CarFull
import com.example.individual.model.GasStation

@Database(
    entities =
    [
        GasStation::class,
        CarFull::class,
    ],
    version = 5
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gasStationDao(): GasStationDao
    abstract fun carDao(): CarDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
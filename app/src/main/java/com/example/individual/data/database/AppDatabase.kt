package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Airline
import com.example.individual.model.PlaneFull

@Database(
    entities =
    [
        Airline::class,
        PlaneFull::class,
    ],
    version = 4
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun airlineDao(): AirlineDao
    abstract fun planeDao(): PlaneDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
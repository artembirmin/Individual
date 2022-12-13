package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Airline

@Database(
    entities =
    [Airline::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun airlineDao(): AirlineDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
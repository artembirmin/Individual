package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.ServiceStation
import com.example.individual.model.WorkOrderFull

@Database(
    entities =
    [
        ServiceStation::class,
        WorkOrderFull::class,
    ],
    version = 5
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceStationDao(): ServiceStationDao
    abstract fun workOrderDao(): WorkOrderDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
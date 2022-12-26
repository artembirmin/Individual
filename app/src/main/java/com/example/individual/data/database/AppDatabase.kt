package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Client
import com.example.individual.model.OrderFull

@Database(
    entities =
    [
        Client::class,
        OrderFull::class,
    ],
    version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao
    abstract fun orderDao(): OrderDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
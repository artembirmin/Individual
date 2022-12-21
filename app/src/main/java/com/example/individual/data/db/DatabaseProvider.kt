package com.example.individual.data.db

import android.content.Context
import androidx.room.Room

class DatabaseProvider(context: Context) {

    private val database: AppDatabase

    init {
        database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun getAirlineDao(): AirlineDao = database.airlineDao()

    fun getPlaneDao(): PlaneDao = database.planeDao()

    companion object {
        private var INSTANCE: DatabaseProvider? = null
        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DatabaseProvider(context)
            }
        }

        fun get(): DatabaseProvider {
            return INSTANCE!!
        }
    }

}
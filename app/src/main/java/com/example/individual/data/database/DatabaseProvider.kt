package com.example.individual.data.database

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

    fun getFacultyDao(): FacultyDao = database.facultyDao()

    fun getGroupDao(): GroupDao = database.groupDao()

    fun getStudentDao(): StudentDao = database.studentDao()

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
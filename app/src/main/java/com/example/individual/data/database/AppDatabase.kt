package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.DepartmentFull
import com.example.individual.model.Faculty

@Database(
    entities =
    [
        Faculty::class,
        DepartmentFull::class,
    ],
    version = 4
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
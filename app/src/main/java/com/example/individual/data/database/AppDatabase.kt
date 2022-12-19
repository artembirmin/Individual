package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Faculty
import com.example.individual.model.Group
import com.example.individual.model.Student

@Database(
    entities =
    [
        Faculty::class,
        Group::class,
        Student::class
    ],
    version = 8
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun groupDao(): GroupDao
    abstract fun studentDao(): StudentDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
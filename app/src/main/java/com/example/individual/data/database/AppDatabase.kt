package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Department
import com.example.individual.model.Employee
import com.example.individual.model.Faculty

@Database(
    entities =
    [
        Faculty::class,
        Department::class,
        Employee::class
    ],
    version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun employeeDao(): EmployeeDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
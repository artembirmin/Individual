package com.example.individual.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.individual.model.Faculty
import com.example.individual.model.Group
import com.example.individual.model.Student

/**
 * База данных
 * https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/529-urok-5-room-osnovy.html
 */
@Database(
    entities = // Таблицы
    [
        Faculty::class,
        Group::class,
        Student::class
    ],
    version = 8 // Версия базы. Менять каждый раз при изменении таблиц, иначе работать не будет
)
@TypeConverters(Converters::class) // Конвертеры типов https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/539-urok-11-room-type-converter.html
abstract class AppDatabase : RoomDatabase() {
    abstract fun facultyDao(): FacultyDao
    abstract fun groupDao(): GroupDao
    abstract fun studentDao(): StudentDao

    companion object {
        const val DB_NAME = "individual_app_database"
    }
}
package com.example.individual.data.database

import androidx.room.TypeConverter
import org.joda.time.DateTime

/**
 * Конвертеры типов https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/539-urok-11-room-type-converter.html
 */
object Converters {
    @TypeConverter
    fun fromDate(date: DateTime): Long {
        return date.millis
    }

    @TypeConverter
    fun toDate(time: Long): DateTime {
        return DateTime(time)
    }
}
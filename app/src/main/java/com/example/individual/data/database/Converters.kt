package com.example.individual.data.database

import androidx.room.TypeConverter
import org.joda.time.DateTime
import java.util.*


object Converters {

    @TypeConverter
    fun fromDate(date: DateTime): Long {
        return date.millis
    }

    @TypeConverter
    fun toDate(time: Long): DateTime {
        return DateTime(time)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun toUUID(id: String): UUID {
        return UUID.fromString(id)
    }
}
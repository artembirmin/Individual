package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ServiceStation.TABLE_NAME)
data class ServiceStation(
    val name: String,
    @PrimaryKey val id: Long
) {
    companion object {
        const val TABLE_NAME = "service_station"
    }
}
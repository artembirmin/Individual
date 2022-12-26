package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GasStation.TABLE_NAME)
data class GasStation(
    val name: String,
    @PrimaryKey val id: Long
) {
    companion object {
        const val TABLE_NAME = "gas_station"
    }
}

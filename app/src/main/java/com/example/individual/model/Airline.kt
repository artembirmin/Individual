package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Airline.TABLE_NAME)
data class Airline(
    val name: String,
    @PrimaryKey val id: Long
) {
    companion object {
        const val TABLE_NAME = "airline"
    }
}

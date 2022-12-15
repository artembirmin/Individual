package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = Airline.TABLE_NAME)
data class Airline(
    val name: String,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
) {
    companion object {
        const val TABLE_NAME = "airline"
    }
}

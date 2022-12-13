package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Airline(
    val name: String,
    @PrimaryKey val id: UUID = UUID.randomUUID()
) {
    companion object {
        const val TABLE_NAME = "model"
    }
}

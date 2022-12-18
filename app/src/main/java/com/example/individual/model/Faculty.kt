package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Faculty.TABLE_NAME)
data class Faculty(
    val name: String,
    @PrimaryKey val id: Long
) {
    companion object {
        const val TABLE_NAME = "faculty"
    }
}

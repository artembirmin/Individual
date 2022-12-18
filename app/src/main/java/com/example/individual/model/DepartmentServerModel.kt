package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

data class DepartmentServerModel(
    @PrimaryKey val id: Long,
    val faculty: Long,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingDateTime: Long,
    val gate: String,
    val firstPilotName: String,
    val secondPilotName: String,
) {
    fun toDepartmentFull(): DepartmentFull = DepartmentFull(
        id,
        faculty,
        onboardNumber,
        flightNumber,
        flightFrom,
        flightTo,
        boardingDateTime.fromServerTimestamp(),
        gate,
        firstPilotName,
        secondPilotName
    )
}
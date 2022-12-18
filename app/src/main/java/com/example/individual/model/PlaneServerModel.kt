package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

data class PlaneServerModel(
    @PrimaryKey val id: Long,
    val airline: Long,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingDateTime: Long,
    val gate: String,
    val firstPilotName: String,
    val secondPilotName: String,
) {
    fun toPlaneFull(): PlaneFull = PlaneFull(
        id,
        airline,
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
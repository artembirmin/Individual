package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

data class CarServerModel(
    @PrimaryKey val id: Long,
    val gasStation: Long,
    val number: String,
    val color: String,
    val vehicleType: String,
    val ownerName: String,
    val passengersCount: Int,
    val fuelingTime: Long,
    val fuelType: String,
    val fuelVolume: Int,
) {
    fun toCarFull(): CarFull = CarFull(
        id = id,
        gasStationId = gasStation,
        number = number,
        color = color,
        vehicleType = vehicleType,
        ownerName = ownerName,
        fuelingTime = fuelingTime.fromServerTimestamp(),
        passengersCount = passengersCount,
        fuelType = fuelType,
        fuelVolume = fuelVolume
    )
}
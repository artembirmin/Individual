package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp
import org.joda.time.DateTime

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
        fuelingDate = run {
            val dateTime = fuelingTime.fromServerTimestamp()
            return@run DateTime(
                dateTime.year,
                dateTime.monthOfYear,
                dateTime.dayOfMonth,
                0,
                0
            )
        },
        passengersCount = passengersCount,
        fuelType = fuelType,
        fuelVolume = fuelVolume
    )
}
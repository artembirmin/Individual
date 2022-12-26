package com.example.individual.model

data class CarShort(
    val id: Long,
    val gasStationId: Long,
    val number: String,
    val fuelType: String,
    val fuelVolume: Int,
)
package com.example.individual.model

import org.joda.time.DateTime

data class PlaneShort(
    val id: Long,
    val airlineId: Long,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingDateTime: DateTime,
)
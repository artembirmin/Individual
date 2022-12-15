package com.example.individual.model

import org.joda.time.DateTime

data class PlaneShort(
    val id: String,
    val airlineId: String,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingTime: DateTime,
)
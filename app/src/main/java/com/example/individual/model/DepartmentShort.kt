package com.example.individual.model

import org.joda.time.DateTime

data class DepartmentShort(
    val id: Long,
    val facultyId: Long,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingDateTime: DateTime,
)
package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.joda.time.DateTime
import java.util.*

@Entity(
    tableName = PlaneFull.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Airline::class,
        parentColumns = ["id"],
        childColumns = ["airlineId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class PlaneFull(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val airlineId: String,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingTime: DateTime,
    val gate: String,
    val firstPilotName: String,
    val secondPilotName: String,
    val seatsCount: Double,
    val maxSpeedKmh: Double
) {
    companion object {
        const val TABLE_NAME = "plane"
    }
}
package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime

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
    @PrimaryKey val id: Long,
    val airlineId: Long,
    val onboardNumber: String,
    val flightNumber: String,
    val flightFrom: String,
    val flightTo: String,
    val boardingDateTime: DateTime,
    val gate: String,
    val firstPilotName: String,
    val secondPilotName: String,
) {
    companion object {
        const val TABLE_NAME = "plane"
    }

    fun toServerModel(): PlaneServerModel =
        PlaneServerModel(
            id,
            airlineId,
            onboardNumber,
            flightNumber,
            flightFrom,
            flightTo,
            boardingDateTime.toServerTimestamp(),
            gate,
            firstPilotName,
            secondPilotName
        )
}
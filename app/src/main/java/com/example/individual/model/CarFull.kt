package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime

@Entity(
    tableName = CarFull.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = GasStation::class,
        parentColumns = ["id"],
        childColumns = ["gasStationId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class CarFull(
    @PrimaryKey val id: Long,
    val gasStationId: Long,
    val number: String,
    val color: String,
    val vehicleType: String,
    val ownerName: String,
    val passengersCount: Int,
    val fuelingTime: DateTime,
    val fuelType: String,
    val fuelVolume: Int,
) {
    companion object {
        const val TABLE_NAME = "car"
    }

    fun toServerModel(): CarServerModel =
        CarServerModel(
            id = id,
            gasStation = gasStationId,
            number = number,
            color = color,
            vehicleType = vehicleType,
            ownerName = ownerName,
            passengersCount = passengersCount,
            fuelingTime = fuelingTime.toServerTimestamp(),
            fuelType = fuelType,
            fuelVolume = fuelVolume
        )
}
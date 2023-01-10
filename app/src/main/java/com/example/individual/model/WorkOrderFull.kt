package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime

@Entity(
    tableName = WorkOrderFull.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = ServiceStation::class,
        parentColumns = ["id"],
        childColumns = ["serviceStationId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class WorkOrderFull(
    @PrimaryKey val id: Long,
    val serviceStationId: Long,
    val number: String,
    val color: String,
    val vehicleType: String,
    val ownerName: String,
    val detailsCount: Int,
    val workDate: DateTime,
    val workerName: String,
    val workingHours: Double,
) {
    companion object {
        const val TABLE_NAME = "work_order"
    }

    fun toServerModel(): WorkOrderServerModel =
        WorkOrderServerModel(
            id = id,
            serviceStation = serviceStationId,
            number = number,
            color = color,
            vehicleType = vehicleType,
            ownerName = ownerName,
            detailsCount = detailsCount,
            workDate = workDate.toServerTimestamp(),
            workerName = workerName,
            workingHours = workingHours
        )
}
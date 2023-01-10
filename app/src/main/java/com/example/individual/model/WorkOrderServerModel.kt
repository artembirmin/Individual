package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

data class WorkOrderServerModel(
    @PrimaryKey val id: Long,
    val serviceStation: Long,
    val number: String,
    val color: String,
    val vehicleType: String,
    val ownerName: String,
    val detailsCount: Int,
    val workDate: Long,
    val workerName: String,
    val workingHours: Double,
) {
    fun toWorkOrderFull(): WorkOrderFull = WorkOrderFull(
        id = id,
        serviceStationId = serviceStation,
        number = number,
        color = color,
        vehicleType = vehicleType,
        ownerName = ownerName,
        workDate = workDate.fromServerTimestamp(),
        detailsCount = detailsCount,
        workerName = workerName,
        workingHours = workingHours
    )
}
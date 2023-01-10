package com.example.individual.model

import org.joda.time.DateTime

data class WorkOrderShort(
    val id: Long,
    val serviceStationId: Long,
    val number: String,
    val workerName: String,
    val workDate: DateTime,
)
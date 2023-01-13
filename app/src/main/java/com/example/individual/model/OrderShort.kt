package com.example.individual.model

import org.joda.time.DateTime

data class OrderShort(
    val id: Long,
    val clientId: Long,
    val amount: Double,
    val unpaidAmount: Double,
    val orderDateTime: DateTime,
    val currency: String,
)
package com.example.individual.model

import org.joda.time.DateTime

data class OrderShort(
    val id: Long,
    val clientId: Long,
    val amount: String,
    val unpaidAmount: Long,
    val orderDateTime: DateTime,
    val currency: String,
)
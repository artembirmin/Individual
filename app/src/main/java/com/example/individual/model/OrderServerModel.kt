package com.example.individual.model

import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

data class OrderServerModel(
    @PrimaryKey val id: Long,
    val client: Long,
    val amount: Double,
    val currency: String,
    val unpaidAmount: Double,
    val deliveryTimeInWorkDays: String,
    val date: Long,
    val weight: Double,
    val deliveryTo: String,
    val deliveryFrom: String,
) {
    fun toOrderFull(): OrderFull = OrderFull(
        id = id,
        clientId = client,
        amount = amount,
        currency = currency,
        unpaidAmount = unpaidAmount,
        deliveryTo = deliveryTo,
        orderDateTime = date.fromServerTimestamp(),
        deliveryTimeInWorkDays = deliveryTimeInWorkDays.toLong(),
        deliveryFrom = deliveryFrom,
        weight = weight
    )
}
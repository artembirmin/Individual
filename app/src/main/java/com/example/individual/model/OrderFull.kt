package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime

@Entity(
    tableName = OrderFull.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Client::class,
        parentColumns = ["id"],
        childColumns = ["clientId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class OrderFull(
    @PrimaryKey val id: Long,
    val clientId: Long,
    val amount: Double,
    val orderDateTime: DateTime,
    val unpaidAmount: Double,
    val weight: Double,
    val currency: String,
    val deliveryTimeInWorkDays: Long,
    val deliveryTo: String,
    val deliveryFrom: String,
) {
    companion object {
        const val TABLE_NAME = "order1"
    }

    fun toServerModel(): OrderServerModel =
        OrderServerModel(
            id = id,
            client = clientId,
            amount = amount,
            currency = currency,
            unpaidAmount = unpaidAmount,
            deliveryTo = deliveryTo,
            deliveryTimeInWorkDays = deliveryTimeInWorkDays.toString(),
            date = orderDateTime.toServerTimestamp(),
            deliveryFrom = deliveryFrom,
            weight = weight
        )
}
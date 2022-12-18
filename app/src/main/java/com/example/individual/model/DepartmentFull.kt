package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime

@Entity(
    tableName = DepartmentFull.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Faculty::class,
        parentColumns = ["id"],
        childColumns = ["facultyId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class DepartmentFull(
    @PrimaryKey val id: Long,
    val facultyId: Long,
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
        const val TABLE_NAME = "department"
    }

    fun toServerModel(): DepartmentServerModel =
        DepartmentServerModel(
            id,
            facultyId,
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
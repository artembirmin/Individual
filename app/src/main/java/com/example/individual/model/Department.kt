package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Department.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Faculty::class,
        parentColumns = ["id"],
        childColumns = ["facultyId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Department(
    @PrimaryKey val id: Long,
    @SerializedName("faculty") val facultyId: Long,
    val name: String,
    val employeesCount: Int,
) {
    companion object {
        const val TABLE_NAME = "department"
    }
}
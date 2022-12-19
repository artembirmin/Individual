package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.individual.utils.toServerTimestamp
import org.joda.time.DateTime
import org.joda.time.Years

@Entity(
    tableName = Employee.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Department::class,
        parentColumns = ["id"],
        childColumns = ["departmentId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Employee(
    @PrimaryKey
    val id: Long,
    val departmentId: Long,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val post: String,
    val dateOfEmployment: DateTime,
    val bio: String?
) {
    companion object {
        const val TABLE_NAME = "employee"
    }

    val experienceInYears: Int
        get() = Years.yearsBetween(dateOfEmployment, DateTime.now()).years

    fun getShortFullName(): String {
        return "$lastName. + ${firstName.first()}. + ${middleName.first()}"
    }

    fun toEmployeeServerModel(): EmployeeServerModel =
        EmployeeServerModel(
            id = id,
            departmentId = departmentId,
            firstName = firstName,
            middleName = middleName,
            lastName = lastName,
            post = post,
            dateOfEmployment = dateOfEmployment.toServerTimestamp(),
            bio = bio
        )
}
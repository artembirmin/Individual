package com.example.individual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.individual.utils.fromServerTimestamp

@Entity(
    tableName = EmployeeServerModel.TABLE_NAME
)
data class EmployeeServerModel(
    @PrimaryKey
    val id: Long,
    val departmentId: Long,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val post: String,
    val dateOfEmployment: Long,
    val bio: String?
) {
    companion object {
        const val TABLE_NAME = "employee"
    }

    fun toEmployee(): Employee =
        Employee(
            id = id,
            departmentId = departmentId,
            firstName = firstName,
            middleName = middleName,
            lastName = lastName,
            post = post,
            dateOfEmployment = dateOfEmployment.fromServerTimestamp(),
            bio = bio
        )
}
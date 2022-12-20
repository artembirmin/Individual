package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Group.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Faculty::class,
        parentColumns = ["id"],
        childColumns = ["facultyId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Group(
    @PrimaryKey val id: Long,
    // При конвертации в Json и обратно, ключем будет имя в скобках
    @SerializedName("faculty") val facultyId: Long,
    val numberGroup: String,
    val special: String,
    val specialCode: String,
    val profile: String,
    val budgetStudentsCount: Int,
    val commerceStudentsCount: Int,
) {
    companion object {
        const val TABLE_NAME = "group1"
    }
}
package com.example.individual.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = Student.TABLE_NAME,
    foreignKeys =
    [ForeignKey(
        entity = Group::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class Student(
    @PrimaryKey
    val id: Long,
    // При конвертации в Json и обратно, ключем будет имя в скобках
    @SerializedName("group") val groupId: Long,
    val fio: String,
    val isOnBudget: Boolean,
) {
    companion object {
        const val TABLE_NAME = "student"
    }
}
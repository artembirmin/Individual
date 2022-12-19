package com.example.individual.presentation.student.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentListFragmentInitParams(
    val groupId: Long,
    val groupName: String
) : InitParams
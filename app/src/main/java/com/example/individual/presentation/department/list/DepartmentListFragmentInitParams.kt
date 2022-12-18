package com.example.individual.presentation.department.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepartmentListFragmentInitParams(
    val facultyId: Long,
    val facultyName: String
) : InitParams
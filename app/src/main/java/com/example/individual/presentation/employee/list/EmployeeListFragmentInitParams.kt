package com.example.individual.presentation.employee.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeListFragmentInitParams(
    val departmentId: Long,
    val departmentName: String
) : InitParams
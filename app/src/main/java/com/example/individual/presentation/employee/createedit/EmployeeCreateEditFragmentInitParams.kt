package com.example.individual.presentation.employee.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeCreateEditFragmentInitParams(
    val departmentId: Long,
    val id: Long? = null
) : InitParams
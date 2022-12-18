package com.example.individual.presentation.department.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepartmentCreateEditFragmentInitParams(
    val facultyId: Long,
    val id: Long? = null
) : InitParams
package com.example.individual.presentation.student.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentCreateEditFragmentInitParams(
    val groupId: Long,
    val id: Long? = null
) : InitParams
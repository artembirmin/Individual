package com.example.individual.presentation.group.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupCreateEditFragmentInitParams(
    val facultyId: Long,
    val id: Long? = null
) : InitParams
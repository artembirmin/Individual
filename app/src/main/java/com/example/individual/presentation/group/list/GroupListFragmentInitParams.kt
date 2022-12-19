package com.example.individual.presentation.group.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupListFragmentInitParams(
    val facultyId: Long,
    val facultyName: String
) : InitParams
package com.example.individual.presentation.workorder.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkOrderCreateEditFragmentInitParams(
    val serviceStationId: Long,
    val id: Long? = null
) : InitParams
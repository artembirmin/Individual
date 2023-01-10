package com.example.individual.presentation.workorder.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkOrderListFragmentInitParams(
    val serviceStationId: Long,
    val serviceStationName: String
) : InitParams
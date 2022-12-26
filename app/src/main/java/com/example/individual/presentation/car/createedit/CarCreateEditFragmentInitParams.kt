package com.example.individual.presentation.car.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarCreateEditFragmentInitParams(
    val gasStationId: Long,
    val id: Long? = null
) : InitParams
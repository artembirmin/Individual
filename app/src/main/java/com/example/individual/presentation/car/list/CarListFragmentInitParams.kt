package com.example.individual.presentation.car.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarListFragmentInitParams(
    val gasStationId: Long,
    val gasStationName: String
) : InitParams
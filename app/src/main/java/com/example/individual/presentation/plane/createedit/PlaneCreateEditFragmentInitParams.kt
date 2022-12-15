package com.example.individual.presentation.plane.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaneCreateEditFragmentInitParams(
    val airlineId: String,
    val id: String? = null
) : InitParams
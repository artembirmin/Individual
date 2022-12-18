package com.example.individual.presentation.plane.createedit

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaneCreateEditFragmentInitParams(
    val airlineId: Long,
    val id: Long? = null
) : InitParams
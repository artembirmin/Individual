package com.example.individual.presentation.order.list

import com.example.individual.common.InitParams
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderListFragmentInitParams(
    val clientId: Long,
    val clientName: String
) : InitParams
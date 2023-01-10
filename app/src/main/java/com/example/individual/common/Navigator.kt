package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.ServiceStation

interface Navigator {
    fun navigateToWorkOrders(serviceStation: ServiceStation)
    fun navigateToWorkOrderCreateEdit(serviceStationId: Long, workOrderId: Long? = null)
    fun navigateToServiceStationCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
}
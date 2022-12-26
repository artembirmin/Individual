package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Client

interface Navigator {
    fun navigateToOrders(client: Client)
    fun navigateToOrderCreateEdit(clientId: Long, orderId: Long? = null)
    fun navigateToClientCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
}
package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Airline

interface Navigator {
    fun navigateToPlanes(airline: Airline)
    fun navigateToPlaneCreateEdit(airlineId: Long, planeId: Long? = null)
    fun navigateToAirlineCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
}
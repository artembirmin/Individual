package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Airline

interface Navigator {
    fun navigateToPlanes(airline: Airline)
    fun navigateToAirlineCreateEdit(id: String? = null)
    fun exit(fragment: Fragment)
}
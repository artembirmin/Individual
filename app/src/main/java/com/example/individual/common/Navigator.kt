package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.GasStation

interface Navigator {
    fun navigateToCars(gasStation: GasStation)
    fun navigateToCarCreateEdit(gasStationId: Long, carId: Long? = null)
    fun navigateToGasStationCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
}
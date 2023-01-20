package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.GasStation
import org.joda.time.DateTime

interface Navigator {
    fun navigateToCars(gasStation: GasStation, date: DateTime)
    fun navigateToCarCreateEdit(gasStationId: Long, carId: Long? = null)
    fun navigateToGasStationCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
}
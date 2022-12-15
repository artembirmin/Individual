package com.example.individual.common

import androidx.fragment.app.Fragment

interface Navigator {
    fun navigateToPlanes(airlineId: String)
    fun exit(fragment: Fragment)
}
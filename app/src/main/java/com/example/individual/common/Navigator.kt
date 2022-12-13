package com.example.individual.common

import androidx.fragment.app.Fragment

interface Navigator {
    fun navigateToList()
    fun navigateToDetails()
    fun exit(fragment: Fragment)
}
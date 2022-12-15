package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.Airline
import com.example.individual.presentation.airline.createedit.AirlineCreateEditFragment
import com.example.individual.presentation.airline.createedit.AirlineCreateEditFragmentInitParams
import com.example.individual.presentation.airline.list.AirlineListFragment
import com.example.individual.presentation.plane.createedit.PlaneCreateEditFragment
import com.example.individual.presentation.plane.createedit.PlaneCreateEditFragmentInitParams
import com.example.individual.presentation.plane.list.PlaneListFragment
import com.example.individual.presentation.plane.list.PlaneListFragmentInitParams

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(AirlineListFragment.newInstance())

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
        if (supportFragmentManager.fragments.isEmpty()) {
            finish()
        }
    }

    override fun navigateToPlanes(airline: Airline) {
        addFragment(
            PlaneListFragment.newInstance(
                PlaneListFragmentInitParams(
                    airline.id,
                    airline.name
                )
            )
        )
    }

    override fun navigateToPlaneCreateEdit(airlineId: String, planeId: String?) {
        addFragment(
            PlaneCreateEditFragment.newInstance(
                PlaneCreateEditFragmentInitParams(
                    airlineId,
                    planeId
                )
            )
        )
    }

    override fun navigateToAirlineCreateEdit(id: String?) {
        addFragment(AirlineCreateEditFragment.newInstance(AirlineCreateEditFragmentInitParams(id)))
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
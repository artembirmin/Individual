package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.GasStation
import com.example.individual.presentation.car.createedit.CarCreateEditFragment
import com.example.individual.presentation.car.createedit.CarCreateEditFragmentInitParams
import com.example.individual.presentation.car.list.CarListFragment
import com.example.individual.presentation.car.list.CarListFragmentInitParams
import com.example.individual.presentation.gasstation.createedit.GasStationCreateEditFragment
import com.example.individual.presentation.gasstation.createedit.GasStationCreateEditFragmentInitParams
import com.example.individual.presentation.gasstation.list.GasStationListFragment
import org.joda.time.DateTime

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(GasStationListFragment.newInstance())

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

    override fun navigateToCars(gasStation: GasStation, date: DateTime) {
        addFragment(
            CarListFragment.newInstance(
                CarListFragmentInitParams(
                    gasStationId = gasStation.id,
                    gasStationName = gasStation.name,
                    date = date
                )
            )
        )
    }

    override fun navigateToCarCreateEdit(gasStationId: Long, carId: Long?) {
        addFragment(
            CarCreateEditFragment.newInstance(
                CarCreateEditFragmentInitParams(
                    gasStationId,
                    carId
                )
            )
        )
    }

    override fun navigateToGasStationCreateEdit(id: Long?) {
        addFragment(
            GasStationCreateEditFragment.newInstance(
                GasStationCreateEditFragmentInitParams(
                    id
                )
            )
        )
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
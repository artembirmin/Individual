package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.ServiceStation
import com.example.individual.presentation.servicestation.createedit.ServiceStationCreateEditFragment
import com.example.individual.presentation.servicestation.createedit.ServiceStationCreateEditFragmentInitParams
import com.example.individual.presentation.servicestation.list.ServiceStationListFragment
import com.example.individual.presentation.workorder.createedit.WorkOrderCreateEditFragment
import com.example.individual.presentation.workorder.createedit.WorkOrderCreateEditFragmentInitParams
import com.example.individual.presentation.workorder.list.WorkOrderListFragment
import com.example.individual.presentation.workorder.list.WorkOrderListFragmentInitParams

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ServiceStationListFragment.newInstance())

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

    override fun navigateToWorkOrders(serviceStation: ServiceStation) {
        addFragment(
            WorkOrderListFragment.newInstance(
                WorkOrderListFragmentInitParams(
                    serviceStation.id,
                    serviceStation.name
                )
            )
        )
    }

    override fun navigateToWorkOrderCreateEdit(serviceStationId: Long, workOrderId: Long?) {
        addFragment(
            WorkOrderCreateEditFragment.newInstance(
                WorkOrderCreateEditFragmentInitParams(
                    serviceStationId,
                    workOrderId
                )
            )
        )
    }

    override fun navigateToServiceStationCreateEdit(id: Long?) {
        addFragment(
            ServiceStationCreateEditFragment.newInstance(
                ServiceStationCreateEditFragmentInitParams(
                    id
                )
            )
        )
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.Client
import com.example.individual.presentation.client.createedit.ClientCreateEditFragment
import com.example.individual.presentation.client.createedit.ClientCreateEditFragmentInitParams
import com.example.individual.presentation.client.list.ClientListFragment
import com.example.individual.presentation.order.createedit.OrderCreateEditFragment
import com.example.individual.presentation.order.createedit.OrderCreateEditFragmentInitParams
import com.example.individual.presentation.order.list.OrderListFragment
import com.example.individual.presentation.order.list.OrderListFragmentInitParams

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ClientListFragment.newInstance())

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

    override fun navigateToOrders(client: Client) {
        addFragment(
            OrderListFragment.newInstance(
                OrderListFragmentInitParams(
                    client.id,
                    client.name
                )
            )
        )
    }

    override fun navigateToOrderCreateEdit(clientId: Long, orderId: Long?) {
        addFragment(
            OrderCreateEditFragment.newInstance(
                OrderCreateEditFragmentInitParams(
                    clientId,
                    orderId
                )
            )
        )
    }

    override fun navigateToClientCreateEdit(id: Long?) {
        addFragment(
            ClientCreateEditFragment.newInstance(
                ClientCreateEditFragmentInitParams(
                    id
                )
            )
        )
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
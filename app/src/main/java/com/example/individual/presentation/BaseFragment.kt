package com.example.individual.presentation

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.individual.common.Navigator

open class BaseFragment : Fragment() {
    protected open var navigator: Navigator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    open fun closeFragment() {
        (requireActivity() as Navigator).exit(this)
    }

    override fun onDestroy() {
        navigator = null
        super.onDestroy()
    }
}
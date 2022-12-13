package com.example.individual.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.individual.common.Navigator

open class BaseFragment : Fragment() {
    private var navigator: Navigator? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
    }

    override fun onDestroy() {
        navigator = null
        super.onDestroy()
    }
}
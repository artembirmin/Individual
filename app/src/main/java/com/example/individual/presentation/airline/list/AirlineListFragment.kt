package com.example.individual.presentation.airline.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentAirlinesListBinding
import com.example.individual.model.Airline
import com.example.individual.presentation.BaseFragment

class AirlineListFragment : BaseFragment() {
    private lateinit var binding: FragmentAirlinesListBinding
    private lateinit var viewModel: AirlineListViewModel
    private val adapter by lazy { AirlinesAdapter(::onItemClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_airlines_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAirlines.adapter = adapter
        viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        viewModel.airlinesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(airlines: List<Airline>) {
        adapter.items = airlines
    }

    private fun onItemClick(item: Airline) {

    }

    companion object {
        fun newInstance() = AirlineListFragment()
    }
}

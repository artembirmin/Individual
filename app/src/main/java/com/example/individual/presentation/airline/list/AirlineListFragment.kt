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

    private val adapter by lazy {
        AirlinesAdapter(
            onAirlineClick = { airline ->
                navigator?.navigateToPlanes(airline)
            },
            onEditAirlineClick = { airline ->
                navigator?.navigateToAirlineCreateEdit(airline.id)
            },
            onDeleteAirlineClick = { airline ->
                viewModel.onDeleteAirlineClick(airline)
            }
        )
    }

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
        binding.btnAdd.setOnClickListener { navigator?.navigateToAirlineCreateEdit() }

        viewModel = ViewModelProvider(this).get(AirlineListViewModel::class.java)
        viewModel.getAirlines()
        viewModel.airlinesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(airlines: List<Airline>) {
        adapter.items = airlines.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = AirlineListFragment()
    }
}

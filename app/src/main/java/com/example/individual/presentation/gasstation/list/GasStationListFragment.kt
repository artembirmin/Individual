package com.example.individual.presentation.gasstation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentGasStationsListBinding
import com.example.individual.model.GasStation
import com.example.individual.presentation.BaseFragment

class GasStationListFragment : BaseFragment() {
    private lateinit var binding: FragmentGasStationsListBinding
    private lateinit var viewModel: GasStationListViewModel

    private val adapter by lazy {
        GasStationsAdapter(
            onGasStationClick = { gasStation ->
                navigator?.navigateToCars(gasStation)
            },
            onFullInfoClick = { gasStation ->
                navigator?.navigateToGasStationCreateEdit(gasStation.id)
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_gas_stations_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGasStations.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToGasStationCreateEdit() }

        viewModel = ViewModelProvider(this).get(GasStationListViewModel::class.java)
        viewModel.getGasStations()
        viewModel.gasStationsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(gasStations: List<GasStation>) {
        adapter.items = gasStations.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = GasStationListFragment()
    }
}

package com.example.individual.presentation.servicestation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentStationsListBinding
import com.example.individual.model.ServiceStation
import com.example.individual.presentation.BaseFragment

class ServiceStationListFragment : BaseFragment() {
    private lateinit var binding: FragmentStationsListBinding
    private lateinit var viewModel: ServiceStationListViewModel

    private val adapter by lazy {
        ServiceStationsAdapter(
            onServiceStationClick = { serviceStation ->
                navigator?.navigateToWorkOrders(serviceStation)
            },
            onFullInfoClick = { serviceStation ->
                navigator?.navigateToServiceStationCreateEdit(serviceStation.id)
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stations_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvServiceStations.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToServiceStationCreateEdit() }

        viewModel = ViewModelProvider(this).get(ServiceStationListViewModel::class.java)
        viewModel.getServiceStations()
        viewModel.serviceStationsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(serviceStations: List<ServiceStation>) {
        adapter.items = serviceStations.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = ServiceStationListFragment()
    }
}

package com.example.individual.presentation.plane.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentPlanesListBinding
import com.example.individual.model.PlaneShort
import com.example.individual.presentation.BaseFragment

class PlaneListFragment : BaseFragment() {
    private lateinit var binding: FragmentPlanesListBinding
    private lateinit var viewModel: PlaneListViewModel

    private val adapter by lazy {
        PlanesAdapter(
            onFullInfoClick = { planeShort -> navigator },
            onBoardNumberClick = {
                sortByBoardNumber()
                showMessageByToast("Сортировка по бортовому номеру")
            },
            onFlightNumberClick = {
                sortByFlightNumber()
                showMessageByToast("Сортировка по номеру рейса")
            })
    }

    private fun sortByBoardNumber() {
        adapter.items = adapter.items.sortedBy { it.onboardNumber }
    }

    private fun sortByFlightNumber() {
        adapter.items = adapter.items.sortedBy { it.flightNumber }
    }

    private val initParams: PlaneListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_planes_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPlanes.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.airlineName

        viewModel = ViewModelProvider(this).get(PlaneListViewModel::class.java)
        viewModel.getPlanes(initParams.airlineId)
        viewModel.planesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(planeFulls: List<PlaneShort>) {
        adapter.items = planeFulls
    }

    companion object {
        fun newInstance(planeListFragmentInitParams: PlaneListFragmentInitParams): PlaneListFragment =
            PlaneListFragment().provideInitParams(planeListFragmentInitParams) as PlaneListFragment
    }
}

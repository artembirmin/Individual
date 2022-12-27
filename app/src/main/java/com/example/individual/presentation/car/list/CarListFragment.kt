package com.example.individual.presentation.car.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentCarsListBinding
import com.example.individual.model.CarShort
import com.example.individual.presentation.BaseFragment

class CarListFragment : BaseFragment() {
    private lateinit var binding: FragmentCarsListBinding
    private lateinit var viewModel: CarListViewModel

    private val adapter by lazy {
        CarsAdapter(
            onFullInfoClick = { carShort ->
                navigator?.navigateToCarCreateEdit(
                    carShort.gasStationId,
                    carShort.id
                )
            },
            onNumberClick = {
                sortByNumber()
                showMessageByToast("Сортировка по номеру")
            },
            onFuelTypeClick = {
                sortByFuelType()
                showMessageByToast("Сортировка по типу топлива")
            })
    }

    private fun sortByNumber() {
        adapter.items = adapter.items.sortedBy { it.number }
    }

    private fun sortByFuelType() {
        adapter.items = adapter.items.sortedBy { it.fuelType }
    }

    private val initParams: CarListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cars_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCars.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.gasStationName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToCarCreateEdit(initParams.gasStationId)
        }

        viewModel = ViewModelProvider(this).get(CarListViewModel::class.java)
        viewModel.getCars(initParams.gasStationId)
        viewModel.carsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(carFulls: List<CarShort>) {
        adapter.items = carFulls.sortedByDescending { it.number }
    }

    companion object {
        fun newInstance(carListFragmentInitParams: CarListFragmentInitParams): CarListFragment =
            CarListFragment().provideInitParams(carListFragmentInitParams) as CarListFragment
    }
}

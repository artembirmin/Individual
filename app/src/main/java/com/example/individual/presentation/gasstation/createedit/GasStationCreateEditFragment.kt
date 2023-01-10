package com.example.individual.presentation.gasstation.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentGasStationCreateEditBinding
import com.example.individual.model.GasStation
import com.example.individual.presentation.BaseFragment

class GasStationCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentGasStationCreateEditBinding
    private lateinit var viewModel: GasStationCreateEditViewModel
    private val initParams: GasStationCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_gas_station_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            binding.etName.clearFocus()
            val name = binding.etName.text.toString()
            if (name.isBlank()) {
                showMessageByToast("Введите название")
                return@setOnClickListener
            }
            viewModel.saveGasStation(GasStation(name = name, id = 0))
            closeFragment()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteGasStation()
            closeFragment()
        }

        viewModel = ViewModelProvider(this).get(GasStationCreateEditViewModel::class.java)

        val gasStationId = initParams.id
        if (gasStationId != null) {
            viewModel.getGasStation(gasStationId)
        }
        viewModel.gasStationLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(gasStation: GasStation?) {
        if (gasStation != null) {
            binding.etName.setText(gasStation.name)
        }
    }

    companion object {
        fun newInstance(initParams: GasStationCreateEditFragmentInitParams) =
            GasStationCreateEditFragment().provideInitParams(initParams) as GasStationCreateEditFragment
    }
}

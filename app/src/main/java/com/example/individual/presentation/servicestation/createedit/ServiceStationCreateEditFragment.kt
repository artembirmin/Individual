package com.example.individual.presentation.servicestation.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentStationCreateEditBinding
import com.example.individual.model.ServiceStation
import com.example.individual.presentation.BaseFragment

class ServiceStationCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentStationCreateEditBinding
    private lateinit var viewModel: ServiceStationCreateEditViewModel
    private val initParams: ServiceStationCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_station_create_edit,
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
            viewModel.saveServiceStation(ServiceStation(name = name, id = 0))
            closeFragment()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteServiceStation()
            closeFragment()
        }

        viewModel = ViewModelProvider(this).get(ServiceStationCreateEditViewModel::class.java)

        val serviceStationId = initParams.id
        if (serviceStationId != null) {
            viewModel.getServiceStation(serviceStationId)
        }
        viewModel.serviceStationLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(serviceStation: ServiceStation?) {
        if (serviceStation != null) {
            binding.etName.setText(serviceStation.name)
        }
    }

    companion object {
        fun newInstance(initParams: ServiceStationCreateEditFragmentInitParams) =
            ServiceStationCreateEditFragment().provideInitParams(initParams) as ServiceStationCreateEditFragment
    }
}

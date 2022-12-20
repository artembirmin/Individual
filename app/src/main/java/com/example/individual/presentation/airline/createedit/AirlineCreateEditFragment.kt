package com.example.individual.presentation.airline.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentAirlineCreateEditBinding
import com.example.individual.model.Airline
import com.example.individual.presentation.BaseFragment

class AirlineCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentAirlineCreateEditBinding
    override lateinit var viewModel: AirlineCreateEditViewModel
    private val initParams: AirlineCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_airline_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(AirlineCreateEditViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            binding.etName.clearFocus()
            val name = binding.etName.text.toString()
            if (name.isBlank()) {
                showMessageByToast("Введите название")
                return@setOnClickListener
            }
            viewModel.saveAirline(Airline(name = name, id = 0))
            closeFragment()
        }

        initParams.id?.let {
            viewModel.getAirline(it)
        }
        viewModel.airlineLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(airline: Airline?) {
        airline?.let {
            binding.etName.setText(airline.name)
        }
    }

    companion object {
        fun newInstance(initParams: AirlineCreateEditFragmentInitParams) =
            AirlineCreateEditFragment().provideInitParams(initParams) as AirlineCreateEditFragment
    }
}

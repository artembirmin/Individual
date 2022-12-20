package com.example.individual.presentation.plane.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentPlaneCreateEditBinding
import com.example.individual.model.PlaneFull
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime
import kotlin.reflect.full.memberProperties

class PlaneCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentPlaneCreateEditBinding
    private lateinit var viewModel: PlaneCreateEditViewModel
    private val initParams: PlaneCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_plane_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tpBoardingTime.setIs24HourView(true)
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }
        }

        viewModel = ViewModelProvider(this).get(PlaneCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getPlane(it)
        }
        viewModel.planeLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {


            val plane = PlaneFull(
                id = 0,
                airlineId = initParams.airlineId,
                onboardNumber = etBoardNumber.text.toString(),
                flightNumber = etFlightNumber.text.toString(),
                flightFrom = etFlightFrom.text.toString(),
                flightTo = etFlightTo.text.toString(),
                boardingDateTime = DateTime(
                    dpBoardingDate.year,
                    dpBoardingDate.month + 1,
                    dpBoardingDate.dayOfMonth,
                    tpBoardingTime.hour,
                    tpBoardingTime.minute
                ),
                gate = etGate.text.toString(),
                firstPilotName = etFirstPilotName.text.toString(),
                secondPilotName = etSecondPilotName.text.toString()
            )
            plane::class.memberProperties.forEach {
                val value = it.getter.call(plane)
                if (value is String) {
                    if (value.isBlank()) {
                        showMessageByToast("Заполните ${it.name}")
                        return
                    }
                }
            }

            viewModel.savePlane(plane)
            closeFragment()
        }
    }

    private fun updateUI(plane: PlaneFull?) {
        plane?.let {
            with(binding) {
                etBoardNumber.setText(plane.onboardNumber)
                etFlightNumber.setText(plane.flightNumber)
                etFlightFrom.setText(plane.flightFrom)
                etBoardNumber.setText(plane.onboardNumber)
                etFlightTo.setText(plane.flightTo)
                dpBoardingDate.updateDate(
                    plane.boardingDateTime.year,
                    plane.boardingDateTime.monthOfYear - 1,
                    plane.boardingDateTime.dayOfMonth
                )
                tpBoardingTime.hour = plane.boardingDateTime.hourOfDay
                tpBoardingTime.minute = plane.boardingDateTime.minuteOfHour
                etGate.setText(plane.gate)
                etFirstPilotName.setText(plane.firstPilotName)
                etSecondPilotName.setText(plane.secondPilotName)
            }
        }
    }

    companion object {
        fun newInstance(initParams: PlaneCreateEditFragmentInitParams) =
            PlaneCreateEditFragment().provideInitParams(initParams) as PlaneCreateEditFragment
    }
}

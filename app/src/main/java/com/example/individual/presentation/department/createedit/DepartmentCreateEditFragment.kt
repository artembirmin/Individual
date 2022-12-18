package com.example.individual.presentation.department.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentDepartmentCreateEditBinding
import com.example.individual.model.DepartmentFull
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime
import kotlin.reflect.full.memberProperties

class DepartmentCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentDepartmentCreateEditBinding
    private lateinit var viewModel: DepartmentCreateEditViewModel
    private val initParams: DepartmentCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_department_create_edit,
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
            btnDelete.setOnClickListener {
                viewModel.deleteDepartment()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteDepartment()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(DepartmentCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getDepartment(it)
        }
        viewModel.departmentLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {


            val department = DepartmentFull(
                id = 0,
                facultyId = initParams.facultyId,
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
            department::class.memberProperties.forEach {
                val value = it.getter.call(department)
                if (value is String) {
                    if (value.isBlank()) {
                        showMessageByToast("Заполните ${it.name}")
                        return
                    }
                }
            }

            viewModel.saveDepartment(department)
            closeFragment()
        }
    }

    private fun updateUI(department: DepartmentFull?) {
        department?.let {
            with(binding) {
                etBoardNumber.setText(department.onboardNumber)
                etFlightNumber.setText(department.flightNumber)
                etFlightFrom.setText(department.flightFrom)
                etBoardNumber.setText(department.onboardNumber)
                etFlightTo.setText(department.flightTo)
                dpBoardingDate.updateDate(
                    department.boardingDateTime.year,
                    department.boardingDateTime.monthOfYear - 1,
                    department.boardingDateTime.dayOfMonth
                )
                tpBoardingTime.hour = department.boardingDateTime.hourOfDay
                tpBoardingTime.minute = department.boardingDateTime.minuteOfHour
                etGate.setText(department.gate)
                etFirstPilotName.setText(department.firstPilotName)
                etSecondPilotName.setText(department.secondPilotName)
            }
        }
    }

    companion object {
        fun newInstance(initParams: DepartmentCreateEditFragmentInitParams) =
            DepartmentCreateEditFragment().provideInitParams(initParams) as DepartmentCreateEditFragment
    }
}

package com.example.individual.presentation.workorder.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrderCreateEditBinding
import com.example.individual.model.WorkOrderFull
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime

class WorkOrderCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderCreateEditBinding
    private lateinit var viewModel: WorkOrderCreateEditViewModel
    private val initParams: WorkOrderCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_order_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }
            btnDelete.setOnClickListener {
                viewModel.deleteWorkOrder()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(WorkOrderCreateEditViewModel::class.java)

        val workOrderId = initParams.id
        if (workOrderId != null) {
            viewModel.getWorkOrder(workOrderId)
        }

        viewModel.workOrderLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val passengersCount = etDetailsCount.text.toString().toIntOrNull() ?: run {
                showMessageByToast("Введите количество деталей")
                return
            }

            val workingHours = etWorkingHours.text.toString().toDoubleOrNull() ?: run {
                showMessageByToast("Введите количество рабочих часов")
                return
            }

            val workOrder = WorkOrderFull(
                id = 0,
                serviceStationId = initParams.serviceStationId,
                number = etNumber.text.toString(),
                color = etColor.text.toString(),
                vehicleType = etVehicleType.text.toString(),
                ownerName = etOwnerName.text.toString(),
                workDate = DateTime(
                    dpWorkDate.year,
                    dpWorkDate.month + 1,
                    dpWorkDate.dayOfMonth,
                    0, 0
                ),
                detailsCount = passengersCount,
                workerName = etWorkerName.text.toString(),
                workingHours = workingHours
            )
            val message = when {
                workOrder.number.isBlank() -> "Введите номер автомобиля"
                workOrder.color.isBlank() -> "Введите цвет автомобиля"
                workOrder.vehicleType.isBlank() -> "Введите тип автомобиля"
                workOrder.ownerName.isBlank() -> "Введите ФИО владельца автомобиля"
                workOrder.workerName.isBlank() -> "Введите ФИО исполнителя работ"
//                workOrder.workDate.isAfterNow -> "Время выполнения работ не может быть больше текущего"
                else -> null
            }
            if (message == null) {
                viewModel.saveWorkOrder(workOrder)
                closeFragment()
            } else {
                showMessageByToast(message)
            }
        }
    }

    private fun updateUI(workOrder: WorkOrderFull?) {
        if (workOrder != null) {
            with(binding) {
                etNumber.setText(workOrder.number)
                etColor.setText(workOrder.color)
                etVehicleType.setText(workOrder.vehicleType)
                etNumber.setText(workOrder.number)
                etOwnerName.setText(workOrder.ownerName)
                dpWorkDate.updateDate(
                    workOrder.workDate.year,
                    workOrder.workDate.monthOfYear - 1,
                    workOrder.workDate.dayOfMonth
                )
                etDetailsCount.setText(workOrder.detailsCount.toString())
                etWorkerName.setText(workOrder.workerName)
                etWorkingHours.setText(workOrder.workingHours.toString())
            }
        } else {
            with(binding) {
                val now = DateTime.now()
                dpWorkDate.updateDate(
                    now.year,
                    now.monthOfYear - 1,
                    now.dayOfMonth
                )
            }
        }
    }

    companion object {
        fun newInstance(initParams: WorkOrderCreateEditFragmentInitParams) =
            WorkOrderCreateEditFragment().provideInitParams(initParams) as WorkOrderCreateEditFragment
    }
}

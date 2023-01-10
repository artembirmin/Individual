package com.example.individual.presentation.workorder.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.WorkOrderRepository
import com.example.individual.model.WorkOrderFull
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class WorkOrderCreateEditViewModel : ViewModel() {
    val workOrderLiveData = MutableLiveData<WorkOrderFull?>()
    private val workOrderRepository = WorkOrderRepository.getInstance()

    fun getWorkOrder(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            workOrderLiveData.postValue(workOrderRepository.getWorkOrderById(id))
        }
    }

    fun saveWorkOrder(newWorkOrder: WorkOrderFull) {
        viewModelScope.launch(defaultErrorHandler) {
            val oldWorkOrder = workOrderLiveData.value
            if (oldWorkOrder != null) {
                workOrderRepository.update(newWorkOrder.copy(id = oldWorkOrder.id))
            } else {
                workOrderRepository.add(newWorkOrder)
            }
        }
    }

    fun deleteWorkOrder() {
        val workOrder = workOrderLiveData.value
        if (workOrder != null) {
            viewModelScope.launch(defaultErrorHandler) {
                workOrderRepository.delete(workOrder)
            }
        }
    }
}
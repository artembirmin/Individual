package com.example.individual.presentation.workorder.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.WorkOrderRepository
import com.example.individual.model.WorkOrderShort
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class WorkOrderListViewModel : ViewModel() {
    val workOrdersLiveData = MutableLiveData<List<WorkOrderShort>>()
    private val workOrderRepository = WorkOrderRepository.getInstance()

    fun getWorkOrders(serviceStationId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            workOrderRepository.observeWorkOrders(serviceStationId).collect {
                workOrdersLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            workOrderRepository.refreshWorkOrders()
        }
    }
}
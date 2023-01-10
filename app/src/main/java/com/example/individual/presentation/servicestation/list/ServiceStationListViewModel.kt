package com.example.individual.presentation.servicestation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.ServiceStationRepository
import com.example.individual.model.ServiceStation
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class ServiceStationListViewModel : ViewModel() {
    val serviceStationsLiveData = MutableLiveData<List<ServiceStation>>()
    private val serviceStationRepository = ServiceStationRepository.getInstance()

    fun getServiceStations() {
        viewModelScope.launch(defaultErrorHandler) {
            serviceStationRepository.observeServiceStations().collect {
                serviceStationsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { serviceStationRepository.refreshServiceStations() }
    }
}
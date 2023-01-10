package com.example.individual.presentation.servicestation.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.ServiceStationRepository
import com.example.individual.model.ServiceStation
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class ServiceStationCreateEditViewModel : ViewModel() {
    val serviceStationLiveData = MutableLiveData<ServiceStation?>()
    private val serviceStationRepository = ServiceStationRepository.getInstance()

    fun getServiceStation(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            serviceStationLiveData.postValue(serviceStationRepository.getServiceStationById(id))
        }
    }

    fun saveServiceStation(newServiceStation: ServiceStation) {
        viewModelScope.launch(defaultErrorHandler) {
            val oldServiceStation = serviceStationLiveData.value
            if (oldServiceStation != null) {
                serviceStationRepository.update(newServiceStation.copy(id = oldServiceStation.id))
            } else {
                serviceStationRepository.add(newServiceStation)
            }
        }
    }

    fun deleteServiceStation() {
        val serviceStation = serviceStationLiveData.value
        if (serviceStation != null)
            viewModelScope.launch(defaultErrorHandler) {
                serviceStationRepository.delete(serviceStation)
            }
    }
}
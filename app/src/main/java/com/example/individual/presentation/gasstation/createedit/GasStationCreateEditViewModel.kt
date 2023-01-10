package com.example.individual.presentation.gasstation.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.GasStationRepository
import com.example.individual.model.GasStation
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class GasStationCreateEditViewModel : ViewModel() {
    val gasStationLiveData = MutableLiveData<GasStation?>()
    private val gasStationRepository = GasStationRepository.getInstance()

    fun getGasStation(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            gasStationLiveData.postValue(gasStationRepository.getGasStationById(id))
        }
    }

    fun saveGasStation(newGasStation: GasStation) {
        viewModelScope.launch(defaultErrorHandler) {
            val oldGasStation = gasStationLiveData.value
            if (oldGasStation != null) {
                gasStationRepository.update(newGasStation.copy(id = oldGasStation.id))
            } else {
                gasStationRepository.add(newGasStation)
            }
        }
    }

    fun deleteGasStation() {
        val gasStation = gasStationLiveData.value
        if (gasStation != null)
            viewModelScope.launch(defaultErrorHandler) {
                gasStationRepository.delete(gasStation)
            }
    }
}
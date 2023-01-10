package com.example.individual.presentation.gasstation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.GasStationRepository
import com.example.individual.model.GasStation
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class GasStationListViewModel : ViewModel() {
    val gasStationsLiveData = MutableLiveData<List<GasStation>>()
    private val gasStationRepository = GasStationRepository.getInstance()

    fun getGasStations() {
        viewModelScope.launch(defaultErrorHandler) {
            gasStationRepository.observeGasStations().collect {
                gasStationsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { gasStationRepository.refreshGasStations() }
    }
}
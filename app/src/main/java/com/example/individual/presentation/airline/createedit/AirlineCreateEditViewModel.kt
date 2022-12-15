package com.example.individual.presentation.airline.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.AirlineRepository
import com.example.individual.model.Airline
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class AirlineCreateEditViewModel : ViewModel() {
    val airlineLiveData = MutableLiveData<Airline?>()
    private val airlineRepository = AirlineRepository.getInstance()

    fun getAirline(id: String) {
        viewModelScope.launch(defaultErrorHandler) {
            airlineLiveData.postValue(airlineRepository.getAirlineById(id))
        }
    }

    fun saveAirline(name: String) {
        val airline = airlineLiveData.value?.copy(name = name) ?: Airline(name = name)
        viewModelScope.launch {
            airlineRepository.add(airline)
        }
    }
}
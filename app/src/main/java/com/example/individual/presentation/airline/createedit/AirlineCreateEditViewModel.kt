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

    fun getAirline(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            airlineLiveData.postValue(airlineRepository.getAirlineById(id))
        }
    }

    fun saveAirline(newAirline: Airline) {
        viewModelScope.launch(defaultErrorHandler) {
            airlineLiveData.value?.let { oldAirline ->
                airlineRepository.update(newAirline.copy(id = oldAirline.id))
            } ?: airlineRepository.add(newAirline)
        }
    }

    fun deleteAirline() {
        airlineLiveData.value?.let { airline ->
            viewModelScope.launch(defaultErrorHandler) {
                airlineRepository.delete(airline)
            }
        }
    }
}
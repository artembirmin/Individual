package com.example.individual.presentation.airline.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.AirlineRepository
import com.example.individual.model.Airline
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class AirlineListViewModel : ViewModel() {
    val airlinesLiveData = MutableLiveData<List<Airline>>()
    private val airlineRepository = AirlineRepository.getInstance()

    fun getAirlines() {
        viewModelScope.launch(defaultErrorHandler) {
            airlineRepository.observeAirlines().collect {
                airlinesLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { airlineRepository.refreshAirlines() }
    }

    fun onDeleteAirlineClick(airline: Airline) {
        viewModelScope.launch(defaultErrorHandler) {
            airlineRepository.delete(airline)
        }
    }
}
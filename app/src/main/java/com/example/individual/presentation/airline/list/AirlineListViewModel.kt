package com.example.individual.presentation.airline.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.AirlineRepository
import com.example.individual.model.Airline
import com.example.individual.presentation.BaseViewModel
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class AirlineListViewModel : BaseViewModel() {
    val airlinesLiveData = MutableLiveData<List<Airline>>()
    private val airlineRepository = AirlineRepository.getInstance()

    fun getAirlines() {
        viewModelScope.launch(defaultErrorHandler) {
            airlineRepository.observeAirlines().collect {
                airlinesLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            showLoader()
            airlineRepository.refreshAirlines()
        }.invokeOnCompletion { hideLoader() }
    }

    fun onDeleteAirlineClick(airline: Airline) {
        viewModelScope.launch(defaultErrorHandler) {
            showLoader()
            airlineRepository.delete(airline)
        }.invokeOnCompletion { hideLoader() }
    }
}
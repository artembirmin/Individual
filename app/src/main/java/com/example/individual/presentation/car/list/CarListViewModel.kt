package com.example.individual.presentation.car.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.CarRepository
import com.example.individual.model.CarShort
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class CarListViewModel : ViewModel() {
    val carsLiveData = MutableLiveData<List<CarShort>>()
    private val carRepository = CarRepository.getInstance()

    fun getCars(gasStationId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            carRepository.observeCars(gasStationId).collect {
                carsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            carRepository.refreshCars()
        }
    }
}
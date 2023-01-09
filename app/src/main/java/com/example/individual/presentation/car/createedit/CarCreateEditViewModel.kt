package com.example.individual.presentation.car.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.CarRepository
import com.example.individual.model.CarFull
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class CarCreateEditViewModel : ViewModel() {
    val carLiveData = MutableLiveData<CarFull?>()
    private val carRepository = CarRepository.getInstance()

    fun getCar(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            carLiveData.postValue(carRepository.getCarById(id))
        }
    }

    fun saveCar(newCar: CarFull) {
        viewModelScope.launch(defaultErrorHandler) {
            // TODO сделать через if
            carLiveData.value?.let {
                carRepository.update(newCar.copy(id = it.id))
            } ?: carRepository.add(newCar)
        }
    }

    fun deleteCar() {
        carLiveData.value?.let { car ->
            viewModelScope.launch(defaultErrorHandler) {
                carRepository.delete(car)
            }
        }
    }
}
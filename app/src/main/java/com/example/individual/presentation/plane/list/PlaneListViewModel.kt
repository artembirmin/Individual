package com.example.individual.presentation.plane.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.PlaneRepository
import com.example.individual.model.PlaneShort
import kotlinx.coroutines.launch

class PlaneListViewModel : ViewModel() {
    val planesLiveData = MutableLiveData<List<PlaneShort>>()
    private val planeRepository = PlaneRepository.getInstance()

    fun getPlanes(airlineId: String) {
        viewModelScope.launch {
            planeRepository.observePlanes(airlineId).collect {
                planesLiveData.postValue(it)
            }
        }
        viewModelScope.launch {
            planeRepository.refreshPlanes()
        }
    }
}
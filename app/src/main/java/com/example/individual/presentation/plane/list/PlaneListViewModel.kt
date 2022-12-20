package com.example.individual.presentation.plane.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.PlaneRepository
import com.example.individual.model.PlaneShort
import com.example.individual.presentation.BaseViewModel
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class PlaneListViewModel : BaseViewModel() {
    val planesLiveData = MutableLiveData<List<PlaneShort>>()
    private val planeRepository = PlaneRepository.getInstance()

    fun getPlanes(airlineId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            planeRepository.observePlanes(airlineId).collect {
                planesLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            showLoader()
            planeRepository.refreshPlanes()
        }.invokeOnCompletion { hideLoader() }
    }

    fun onDeleteClick(planeShort: PlaneShort) {
        viewModelScope.launch(defaultErrorHandler) {
            showLoader()
            planeRepository.deleteById(planeShort.id)
        }.invokeOnCompletion { hideLoader() }
    }
}
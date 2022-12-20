package com.example.individual.presentation.plane.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.PlaneRepository
import com.example.individual.model.PlaneFull
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class PlaneCreateEditViewModel : ViewModel() {
    val planeLiveData = MutableLiveData<PlaneFull?>()
    private val planeRepository = PlaneRepository.getInstance()

    fun getPlane(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            planeLiveData.postValue(planeRepository.getPlaneById(id))
        }
    }

    fun savePlane(newPlane: PlaneFull) {
        viewModelScope.launch(defaultErrorHandler) {
            planeLiveData.value?.let {
                planeRepository.update(newPlane.copy(id = it.id))
            } ?: planeRepository.add(newPlane)
        }
    }
}
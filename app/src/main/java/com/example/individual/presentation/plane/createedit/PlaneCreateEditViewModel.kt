package com.example.individual.presentation.plane.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.PlaneRepository
import com.example.individual.model.PlaneFull
import com.example.individual.presentation.BaseViewModel
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class PlaneCreateEditViewModel : BaseViewModel() {
    val planeLiveData = MutableLiveData<PlaneFull?>()
    private val planeRepository = PlaneRepository.getInstance()

    fun getPlane(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            planeLiveData.postValue(planeRepository.getPlaneById(id))
        }
    }

    fun savePlane(newPlane: PlaneFull) {
        viewModelScope.launch(defaultErrorHandler) {
            showLoader()
            planeLiveData.value?.let {
                planeRepository.update(newPlane.copy(id = it.id))
            } ?: planeRepository.add(newPlane)
        }.invokeOnCompletion { hideLoader() }
    }
}
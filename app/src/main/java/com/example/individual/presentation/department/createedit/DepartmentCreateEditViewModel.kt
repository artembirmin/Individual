package com.example.individual.presentation.department.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.DepartmentRepository
import com.example.individual.model.DepartmentFull
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class DepartmentCreateEditViewModel : ViewModel() {
    val departmentLiveData = MutableLiveData<DepartmentFull?>()
    private val departmentRepository = DepartmentRepository.getInstance()

    fun getDepartment(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.postValue(departmentRepository.getDepartmentById(id))
        }
    }

    fun saveDepartment(newDepartment: DepartmentFull) {
        viewModelScope.launch(defaultErrorHandler) {
            departmentLiveData.value?.let {
                departmentRepository.update(newDepartment.copy(id = it.id))
            } ?: departmentRepository.add(newDepartment)
        }
    }

    fun deleteDepartment() {
        departmentLiveData.value?.let { department ->
            viewModelScope.launch(defaultErrorHandler) {
                departmentRepository.delete(department)
            }
        }
    }
}
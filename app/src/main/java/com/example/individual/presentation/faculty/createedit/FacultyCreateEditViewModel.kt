package com.example.individual.presentation.faculty.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.FacultyRepository
import com.example.individual.model.Faculty
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class FacultyCreateEditViewModel : ViewModel() {
    val facultyLiveData = MutableLiveData<Faculty?>()
    private val facultyRepository = FacultyRepository.getInstance()

    fun getFaculty(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            facultyLiveData.postValue(facultyRepository.getFacultyById(id))
        }
    }

    fun saveFaculty(newFaculty: Faculty) {
        viewModelScope.launch(defaultErrorHandler) {
            facultyLiveData.value?.let { oldFaculty ->
                facultyRepository.update(
                    newFaculty.copy(id = oldFaculty.id)
                )
            } ?: facultyRepository.add(newFaculty)
        }
    }

    fun deleteFaculty() {
        facultyLiveData.value?.let { faculty ->
            viewModelScope.launch(defaultErrorHandler) {
                facultyRepository.delete(faculty)
            }
        }
    }
}
package com.example.individual.presentation.faculty.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.FacultyRepository
import com.example.individual.model.Faculty
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class FacultyListViewModel : ViewModel() {
    val facultiesLiveData = MutableLiveData<List<Faculty>>()
    private val facultyRepository = FacultyRepository.getInstance()

    fun getFaculties() {
        // Подписались на изменения базы
        viewModelScope.launch(defaultErrorHandler) {
            facultyRepository.observeFaculties().collect {
                facultiesLiveData.postValue(it)
            }
        }
        // Обновили базу с бэка
        viewModelScope.launch(defaultErrorHandler) { facultyRepository.refreshFaculties() }
    }
}
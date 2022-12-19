package com.example.individual.presentation.student.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.StudentRepository
import com.example.individual.model.Student
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class StudentListViewModel : ViewModel() {
    val studentsLiveData = MutableLiveData<List<Student>>()
    private val studentRepository = StudentRepository.getInstance()

    fun getStudents(facultyId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            studentRepository.observeStudents(facultyId).collect {
                studentsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            studentRepository.refreshStudents()
        }
    }
}
package com.example.individual.presentation.student.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.StudentRepository
import com.example.individual.model.Student
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class StudentCreateEditViewModel : ViewModel() {
    val studentLiveData = MutableLiveData<Student?>()
    private val studentRepository = StudentRepository.getInstance()

    fun getStudent(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            studentLiveData.postValue(studentRepository.getStudentById(id))
        }
    }

    fun saveStudent(newStudent: Student) {
        viewModelScope.launch(defaultErrorHandler) {
            studentLiveData.value?.let { oldStudent ->
                studentRepository.update(newStudent.copy(id = oldStudent.id))
            } ?: studentRepository.add(newStudent)
        }
    }

    fun deleteStudent() {
        studentLiveData.value?.let { student ->
            viewModelScope.launch(defaultErrorHandler) {
                studentRepository.delete(student)
            }
        }
    }
}
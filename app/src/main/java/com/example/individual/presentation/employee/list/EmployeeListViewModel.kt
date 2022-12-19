package com.example.individual.presentation.employee.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.EmployeeRepository
import com.example.individual.model.Employee
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class EmployeeListViewModel : ViewModel() {
    val employeesLiveData = MutableLiveData<List<Employee>>()
    private val employeeRepository = EmployeeRepository.getInstance()

    fun getEmployees(facultyId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            employeeRepository.observeEmployees(facultyId).collect {
                employeesLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            employeeRepository.refreshEmployees()
        }
    }
}
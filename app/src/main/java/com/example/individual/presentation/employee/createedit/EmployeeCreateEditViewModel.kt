package com.example.individual.presentation.employee.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.EmployeeRepository
import com.example.individual.model.Employee
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class EmployeeCreateEditViewModel : ViewModel() {
    val employeeLiveData = MutableLiveData<Employee?>()
    private val employeeRepository = EmployeeRepository.getInstance()

    fun getEmployee(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            employeeLiveData.postValue(employeeRepository.getEmployeeById(id))
        }
    }

    fun saveEmployee(newEmployee: Employee) {
        viewModelScope.launch(defaultErrorHandler) {
            employeeLiveData.value?.let {
                employeeRepository.update(newEmployee.copy(id = it.id))
            } ?: employeeRepository.add(newEmployee)
        }
    }

    fun deleteEmployee() {
        employeeLiveData.value?.let { employee ->
            viewModelScope.launch(defaultErrorHandler) {
                employeeRepository.delete(employee)
            }
        }
    }
}
package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Employee
import kotlinx.coroutines.flow.Flow

class EmployeeRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val employeeDao = DatabaseProvider.get().getEmployeeDao()

    fun observeEmployees(employeeId: Long): Flow<List<Employee>> {
        return employeeDao.getAll(employeeId)
    }

    suspend fun refreshEmployees() {
        val employees = individualApi.getEmployees().map { it.toEmployee() }
        employeeDao.insertAll(employees)
    }

    suspend fun add(newEmployee: Employee) {
        val employee =
            individualApi.addEmployee(newEmployee.toEmployeeServerModel()).toEmployee()
        employeeDao.insert(employee)
    }

    suspend fun update(updatedEmployee: Employee) {
        val employee =
            individualApi.updateEmployee(
                updatedEmployee.id,
                updatedEmployee.toEmployeeServerModel()
            ).toEmployee()

        employeeDao.insert(employee)
    }

    suspend fun delete(employee: Employee) {
        individualApi.deleteEmployee(employee.id)
        employeeDao.delete(employee)
    }

    suspend fun getEmployeeById(id: Long): Employee {
        return employeeDao.getById(id)
    }

    companion object {
        private var INSTANCE: EmployeeRepository? = null
        fun getInstance(): EmployeeRepository {
            if (INSTANCE == null) {
                INSTANCE = EmployeeRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
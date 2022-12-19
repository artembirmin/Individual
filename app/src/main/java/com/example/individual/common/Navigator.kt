package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Department
import com.example.individual.model.Faculty

interface Navigator {
    fun navigateToDepartments(faculty: Faculty)
    fun navigateToDepartmentCreateEdit(facultyId: Long, departmentId: Long? = null)
    fun navigateToFacultyCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
    fun navigateToEmployeeCreateEdit(departmentId: Long, id: Long? = null)
    fun navigateToEmployees(department: Department)
}
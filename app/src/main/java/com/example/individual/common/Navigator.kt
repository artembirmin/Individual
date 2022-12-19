package com.example.individual.common

import androidx.fragment.app.Fragment
import com.example.individual.model.Faculty
import com.example.individual.model.Group

interface Navigator {
    fun navigateToGroups(faculty: Faculty)
    fun navigateToGroupCreateEdit(facultyId: Long, groupId: Long? = null)
    fun navigateToFacultyCreateEdit(id: Long? = null)
    fun exit(fragment: Fragment)
    fun navigateToStudentCreateEdit(groupId: Long, id: Long? = null)
    fun navigateToStudents(group: Group)
}
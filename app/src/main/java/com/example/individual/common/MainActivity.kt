package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.Faculty
import com.example.individual.presentation.department.createedit.DepartmentCreateEditFragment
import com.example.individual.presentation.department.createedit.DepartmentCreateEditFragmentInitParams
import com.example.individual.presentation.department.list.DepartmentListFragment
import com.example.individual.presentation.department.list.DepartmentListFragmentInitParams
import com.example.individual.presentation.faculty.createedit.FacultyCreateEditFragment
import com.example.individual.presentation.faculty.createedit.FacultyCreateEditFragmentInitParams
import com.example.individual.presentation.faculty.list.FacultyListFragment

class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(FacultyListFragment.newInstance())

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
        if (supportFragmentManager.fragments.isEmpty()) {
            finish()
        }
    }

    override fun navigateToDepartments(faculty: Faculty) {
        addFragment(
            DepartmentListFragment.newInstance(
                DepartmentListFragmentInitParams(
                    faculty.id,
                    faculty.name
                )
            )
        )
    }

    override fun navigateToDepartmentCreateEdit(facultyId: Long, departmentId: Long?) {
        addFragment(
            DepartmentCreateEditFragment.newInstance(
                DepartmentCreateEditFragmentInitParams(
                    facultyId,
                    departmentId
                )
            )
        )
    }

    override fun navigateToFacultyCreateEdit(id: Long?) {
        addFragment(FacultyCreateEditFragment.newInstance(FacultyCreateEditFragmentInitParams(id)))
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
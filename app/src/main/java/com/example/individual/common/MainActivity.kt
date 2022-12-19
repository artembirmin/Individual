package com.example.individual.common

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.individual.R
import com.example.individual.model.Faculty
import com.example.individual.model.Group
import com.example.individual.presentation.faculty.createedit.FacultyCreateEditFragment
import com.example.individual.presentation.faculty.createedit.FacultyCreateEditFragmentInitParams
import com.example.individual.presentation.faculty.list.FacultyListFragment
import com.example.individual.presentation.group.createedit.GroupCreateEditFragment
import com.example.individual.presentation.group.createedit.GroupCreateEditFragmentInitParams
import com.example.individual.presentation.group.list.GroupListFragment
import com.example.individual.presentation.group.list.GroupListFragmentInitParams
import com.example.individual.presentation.student.createedit.StudentCreateEditFragment
import com.example.individual.presentation.student.createedit.StudentCreateEditFragmentInitParams
import com.example.individual.presentation.student.list.StudentListFragment
import com.example.individual.presentation.student.list.StudentListFragmentInitParams

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

    override fun navigateToGroups(faculty: Faculty) {
        addFragment(
            GroupListFragment.newInstance(
                GroupListFragmentInitParams(
                    faculty.id,
                    faculty.name
                )
            )
        )
    }

    override fun navigateToGroupCreateEdit(facultyId: Long, groupId: Long?) {
        addFragment(
            GroupCreateEditFragment.newInstance(
                GroupCreateEditFragmentInitParams(
                    facultyId,
                    groupId
                )
            )
        )
    }

    override fun navigateToFacultyCreateEdit(id: Long?) {
        addFragment(FacultyCreateEditFragment.newInstance(FacultyCreateEditFragmentInitParams(id)))
    }

    override fun navigateToStudentCreateEdit(groupId: Long, id: Long?) {
        addFragment(
            StudentCreateEditFragment.newInstance(
                StudentCreateEditFragmentInitParams(
                    groupId, id
                )
            )
        )
    }

    override fun navigateToStudents(group: Group) {
        addFragment(
            StudentListFragment.newInstance(
                StudentListFragmentInitParams(
                    group.id, group.numberGroup
                )
            )
        )
    }

    override fun exit(fragment: Fragment) {
        removeFragment(fragment)
    }
}
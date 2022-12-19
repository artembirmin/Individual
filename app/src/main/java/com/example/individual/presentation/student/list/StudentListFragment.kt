package com.example.individual.presentation.student.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentStudentsListBinding
import com.example.individual.model.Student
import com.example.individual.presentation.BaseFragment

class StudentListFragment : BaseFragment() {
    private lateinit var binding: FragmentStudentsListBinding
    private lateinit var viewModel: StudentListViewModel

    private val adapter by lazy {
        StudentsAdapter(
            onStudentClick = { student ->
                navigator?.navigateToStudentCreateEdit(
                    student.groupId,
                    student.id
                )
            }
        )
    }

    private val initParams: StudentListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_students_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvStudents.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.groupName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToStudentCreateEdit(initParams.groupId)
        }

        viewModel = ViewModelProvider(this).get(StudentListViewModel::class.java)
        viewModel.getStudents(initParams.groupId)
        viewModel.studentsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(studentFulls: List<Student>) {
        adapter.items = studentFulls.sortedByDescending { it.fio }
    }

    companion object {
        fun newInstance(studentListFragmentInitParams: StudentListFragmentInitParams): StudentListFragment =
            StudentListFragment().provideInitParams(studentListFragmentInitParams) as StudentListFragment
    }
}
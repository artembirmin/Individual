package com.example.individual.presentation.department.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentDepartmentsListBinding
import com.example.individual.model.Department
import com.example.individual.presentation.BaseFragment

class DepartmentListFragment : BaseFragment() {
    private lateinit var binding: FragmentDepartmentsListBinding
    private lateinit var viewModel: DepartmentListViewModel
    private var isDescendingSort = false

    private val adapter by lazy {
        DepartmentsAdapter(
            onFullInfoClick = { departmentShort ->
                navigator?.navigateToDepartmentCreateEdit(
                    departmentShort.facultyId,
                    departmentShort.id
                )
            },
            onNameClick = {
                sortByName()
                showMessageByToast("Сортировка по названию")
            },
            onEmployeesClick = { department ->
                navigator?.navigateToEmployees(department)
            }
        )
    }

    private fun sortByName() {
        if (isDescendingSort) {
            adapter.items = adapter.items.sortedByDescending { it.name }
        } else {
            adapter.items = adapter.items.sortedBy { it.name }
        }
        isDescendingSort = isDescendingSort.not()
    }

    private val initParams: DepartmentListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_departments_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDepartments.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.facultyName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToDepartmentCreateEdit(initParams.facultyId)
        }

        viewModel = ViewModelProvider(this).get(DepartmentListViewModel::class.java)
        viewModel.getDepartments(initParams.facultyId)
        viewModel.departmentsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(departmentFulls: List<Department>) {
        adapter.items = departmentFulls.sortedByDescending { it.employeesCount }
    }

    companion object {
        fun newInstance(departmentListFragmentInitParams: DepartmentListFragmentInitParams): DepartmentListFragment =
            DepartmentListFragment().provideInitParams(departmentListFragmentInitParams) as DepartmentListFragment
    }
}
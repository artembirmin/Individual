package com.example.individual.presentation.employee.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentEmployeesListBinding
import com.example.individual.model.Employee
import com.example.individual.presentation.BaseFragment

class EmployeeListFragment : BaseFragment() {
    private lateinit var binding: FragmentEmployeesListBinding
    private lateinit var viewModel: EmployeeListViewModel

    private val adapter by lazy {
        EmployeesAdapter(
            onEmployeeClick = { employee ->
                navigator?.navigateToEmployeeCreateEdit(
                    employee.departmentId,
                    employee.id
                )
            }
        )
    }

    private val initParams: EmployeeListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_employees_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvEmployees.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.departmentName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToEmployeeCreateEdit(initParams.departmentId)
        }

        viewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        viewModel.getEmployees(initParams.departmentId)
        viewModel.employeesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(employeeFulls: List<Employee>) {
        adapter.items = employeeFulls.sortedByDescending { it.getShortFullName() }
    }

    companion object {
        fun newInstance(employeeListFragmentInitParams: EmployeeListFragmentInitParams): EmployeeListFragment =
            EmployeeListFragment().provideInitParams(employeeListFragmentInitParams) as EmployeeListFragment
    }
}
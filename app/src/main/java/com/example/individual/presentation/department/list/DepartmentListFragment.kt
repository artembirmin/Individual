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
import com.example.individual.model.DepartmentShort
import com.example.individual.presentation.BaseFragment

class DepartmentListFragment : BaseFragment() {
    private lateinit var binding: FragmentDepartmentsListBinding
    private lateinit var viewModel: DepartmentListViewModel

    private val adapter by lazy {
        DepartmentsAdapter(
            onFullInfoClick = { departmentShort ->
                navigator?.navigateToDepartmentCreateEdit(
                    departmentShort.facultyId,
                    departmentShort.id
                )
            },
            onBoardNumberClick = {
                sortByBoardNumber()
                showMessageByToast("Сортировка по бортовому номеру")
            },
            onFlightNumberClick = {
                sortByFlightNumber()
                showMessageByToast("Сортировка по номеру рейса")
            })
    }

    private fun sortByBoardNumber() {
        adapter.items = adapter.items.sortedBy { it.onboardNumber }
    }

    private fun sortByFlightNumber() {
        adapter.items = adapter.items.sortedBy { it.flightNumber }
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

    private fun updateUI(departmentFulls: List<DepartmentShort>) {
        adapter.items = departmentFulls.sortedByDescending { it.boardingDateTime }
    }

    companion object {
        fun newInstance(departmentListFragmentInitParams: DepartmentListFragmentInitParams): DepartmentListFragment =
            DepartmentListFragment().provideInitParams(departmentListFragmentInitParams) as DepartmentListFragment
    }
}

package com.example.individual.presentation.department.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentDepartmentCreateEditBinding
import com.example.individual.model.Department
import com.example.individual.presentation.BaseFragment

class DepartmentCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentDepartmentCreateEditBinding
    private lateinit var viewModel: DepartmentCreateEditViewModel
    private val initParams: DepartmentCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_department_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }
            btnDelete.setOnClickListener {
                viewModel.deleteDepartment()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteDepartment()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(DepartmentCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getDepartment(it)
        }
        viewModel.departmentLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            etName.clearFocus()
            val name = etName.text.toString()
            if (name.isBlank()) {
                showMessageByToast("Введите название")
                return
            }
            val department = Department(
                id = 0,
                facultyId = initParams.facultyId,
                name = etName.text.toString(),
                employeesCount = 0
            )

            viewModel.saveDepartment(department)
            closeFragment()
        }
    }

    private fun updateUI(department: Department?) {
        department?.let {
            with(binding) {
                etName.setText(department.name)
            }
        }
    }

    companion object {
        fun newInstance(initParams: DepartmentCreateEditFragmentInitParams) =
            DepartmentCreateEditFragment().provideInitParams(initParams) as DepartmentCreateEditFragment
    }
}

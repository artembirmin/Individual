package com.example.individual.presentation.employee.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentEmployeeCreateEditBinding
import com.example.individual.model.Employee
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime

class EmployeeCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentEmployeeCreateEditBinding
    private lateinit var viewModel: EmployeeCreateEditViewModel
    private val initParams: EmployeeCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_employee_create_edit, container, false
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
                viewModel.deleteEmployee()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteEmployee()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(EmployeeCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getEmployee(it)
        }
        viewModel.employeeLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val employee = Employee(
                id = 0,
                departmentId = initParams.departmentId,
                firstName = etFirstName.text.toString(),
                middleName = etMiddleName.text.toString(),
                lastName = etLastName.text.toString(),
                post = etPost.text.toString(),
                dateOfEmployment = DateTime().withDate(
                    dpDateOfEmployment.year,
                    dpDateOfEmployment.month + 1,
                    dpDateOfEmployment.dayOfMonth,
                ),
                bio = etBio.text.toString(),
            )

            val message = employee.validate()
            if (message != null) {
                showMessageByToast(message)
                return
            }

            viewModel.saveEmployee(employee)
            closeFragment()
        }
    }

    private fun Employee.validate(): String? {
        return when {
            firstName.isBlank() || lastName.isBlank() || middleName.isBlank() -> "Заполните имя"
            post.isBlank() -> "Заполните должность"
            dateOfEmployment.isAfterNow -> "Дата приема на работу не может быть больше текуещй"
            else -> null
        }
    }

    private fun updateUI(employee: Employee?) {
        employee?.let {
            with(binding) {
                etFirstName.setText(employee.firstName)
                etMiddleName.setText(employee.middleName)
                etLastName.setText(employee.lastName)
                etPost.setText(employee.post)
                etBio.setText(employee.bio)
                dpDateOfEmployment.updateDate(
                    employee.dateOfEmployment.year,
                    employee.dateOfEmployment.monthOfYear - 1,
                    employee.dateOfEmployment.dayOfMonth
                )
            }
        }
    }

    companion object {
        fun newInstance(initParams: EmployeeCreateEditFragmentInitParams) =
            EmployeeCreateEditFragment().provideInitParams(initParams) as EmployeeCreateEditFragment
    }
}

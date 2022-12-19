package com.example.individual.presentation.student.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentStudentCreateEditBinding
import com.example.individual.model.Student
import com.example.individual.presentation.BaseFragment

class StudentCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentStudentCreateEditBinding
    private lateinit var viewModel: StudentCreateEditViewModel
    private val initParams: StudentCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_student_create_edit, container, false
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
                viewModel.deleteStudent()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteStudent()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(StudentCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getStudent(it)
        }
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val fio = etFio.text.toString()
            if (fio.isBlank()) {
                showMessageByToast("Заполните ФИО")
                return
            }
            val student = Student(
                id = 0,
                groupId = initParams.groupId,
                fio = etFio.text.toString(),
                isOnBudget = cbBudget.isChecked
            )

            viewModel.saveStudent(student)
            closeFragment()
        }
    }

    private fun updateUI(student: Student?) {
        student?.let {
            with(binding) {
                etFio.setText(student.fio)
                cbBudget.isChecked = student.isOnBudget
            }
        }
    }

    companion object {
        fun newInstance(initParams: StudentCreateEditFragmentInitParams) =
            StudentCreateEditFragment().provideInitParams(initParams) as StudentCreateEditFragment
    }
}

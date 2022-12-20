package com.example.individual.presentation.faculty.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentFacultyCreateEditBinding
import com.example.individual.model.Faculty
import com.example.individual.presentation.BaseFragment

class FacultyCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentFacultyCreateEditBinding
    private lateinit var viewModel: FacultyCreateEditViewModel
    private val initParams: FacultyCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_faculty_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            onSaveClick()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteFaculty()
            closeFragment()
        }

        viewModel = ViewModelProvider(this).get(FacultyCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getFaculty(it)
        }
        viewModel.facultyLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        binding.etName.clearFocus()
        val name = binding.etName.text.toString()
        if (name.isBlank()) {
            showMessageByToast("Введите название")
            return
        }
        viewModel.saveFaculty(newFaculty = Faculty(name = name, id = 0))
        closeFragment()
    }

    private fun updateUI(faculty: Faculty?) {
        faculty?.let {
            binding.etName.setText(faculty.name)
        }
    }

    companion object {
        fun newInstance(initParams: FacultyCreateEditFragmentInitParams) =
            FacultyCreateEditFragment().provideInitParams(initParams) as FacultyCreateEditFragment
    }
}

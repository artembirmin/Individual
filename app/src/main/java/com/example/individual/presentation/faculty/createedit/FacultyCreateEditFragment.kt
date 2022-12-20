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

/**
 * Фрагмент - https://developer.alexanderklimov.ru/android/theory/fragments.php
 * Наследуется от [BaseFragment]
 */

class FacultyCreateEditFragment : BaseFragment() {
    // Удобная штука для доступа к элементам макета
    // https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/551-urok-18-data-binding-osnovy.html
    private lateinit var binding: FragmentFacultyCreateEditBinding

    // https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/527-urok-4-viewmodel.html
    private lateinit var viewModel: FacultyCreateEditViewModel

    // Получили переданные параметры
    // by lazy -- инициализируется во время первого обращения
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

        // Создание вьюмодели
        viewModel = ViewModelProvider(this).get(FacultyCreateEditViewModel::class.java)

        // Листенер на стрелку Назад
        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            onSaveClick()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteFaculty()
            closeFragment()
        }

        // Из параметров достали id редактируемого факультета и запросили получение данных о нем.
        // Если id == null, значит происходит создание факультета. Тогда данные запрашивать не надо
        initParams.id?.let {
            viewModel.getFaculty(it)
        }
        // Подписались на изменения live data с данными факультета
        viewModel.facultyLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        binding.etName.clearFocus()
        val name = binding.etName.text.toString()
        if (name.isBlank()) {
            //  Показали всплывающее сообщение
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
            // Создали фрагмент и передали ему параметры
            FacultyCreateEditFragment().provideInitParams(initParams) as FacultyCreateEditFragment
    }
}

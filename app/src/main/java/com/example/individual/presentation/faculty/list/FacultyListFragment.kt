package com.example.individual.presentation.faculty.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentFacultiesListBinding
import com.example.individual.model.Faculty
import com.example.individual.presentation.BaseFragment

class FacultyListFragment : BaseFragment() {
    private lateinit var binding: FragmentFacultiesListBinding
    private lateinit var viewModel: FacultyListViewModel

    // Инстанс адаптера
    private val adapter by lazy {
        FacultiesAdapter(
            // Передаем листенеры
            onFacultyClick = { faculty ->
                // Навигируемся на список групп факультета
                navigator?.navigateToGroups(faculty)
            },
            onFacultyLongClick = { faculty ->
                navigator?.navigateToFacultyCreateEdit(faculty.id)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_faculties_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFaculties.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToFacultyCreateEdit() }

        viewModel = ViewModelProvider(this).get(FacultyListViewModel::class.java)
        viewModel.getFaculties()
        viewModel.facultiesLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(faculties: List<Faculty>) {
        adapter.items = faculties.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = FacultyListFragment()
    }
}

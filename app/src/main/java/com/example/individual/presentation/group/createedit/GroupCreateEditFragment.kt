package com.example.individual.presentation.group.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentGroupCreateEditBinding
import com.example.individual.model.Group
import com.example.individual.presentation.BaseFragment

class GroupCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentGroupCreateEditBinding
    private lateinit var viewModel: GroupCreateEditViewModel
    private val initParams: GroupCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_group_create_edit,
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
                viewModel.deleteGroup()
                closeFragment()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteGroup()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(GroupCreateEditViewModel::class.java)
        initParams.id?.let {
            viewModel.getGroup(it)
        }
        viewModel.groupLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {

            val group = Group(
                id = 0,
                facultyId = initParams.facultyId,
                numberGroup = etGroupNumber.text.toString(),
                special = etSpecial.text.toString(),
                specialCode = etSpecialCode.text.toString(),
                profile = etProfile.text.toString(),
                budgetStudentsCount = 0,
                commerceStudentsCount = 0
            )

            val message = group.validate()
            if (message != null) {
                showMessageByToast(message)
                return
            }

            viewModel.saveGroup(group)
            closeFragment()
        }
    }

    private fun Group.validate(): String? {
        return when {
            numberGroup.isBlank() -> "Заполните номер группы"
            special.isBlank() -> "Заполните специальность"
            specialCode.isBlank() -> "Заполните код специальности"
            profile.isBlank() -> "Заполните профиль"
            else -> null
        }
    }

    private fun updateUI(group: Group?) {
        group?.let {
            with(binding) {
                etGroupNumber.setText(group.numberGroup)
                etSpecial.setText(group.special)
                etSpecialCode.setText(group.specialCode)
                etProfile.setText(group.profile)
            }
        }
    }

    companion object {
        fun newInstance(initParams: GroupCreateEditFragmentInitParams) =
            GroupCreateEditFragment().provideInitParams(initParams) as GroupCreateEditFragment
    }
}

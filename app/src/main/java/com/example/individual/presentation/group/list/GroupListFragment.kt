package com.example.individual.presentation.group.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentGroupsListBinding
import com.example.individual.model.Group
import com.example.individual.presentation.BaseFragment

class GroupListFragment : BaseFragment() {
    private lateinit var binding: FragmentGroupsListBinding
    private lateinit var viewModel: GroupListViewModel

    private val adapter by lazy {
        GroupsAdapter(
            onFullInfoClick = { groupShort ->
                navigator?.navigateToGroupCreateEdit(
                    groupShort.facultyId,
                    groupShort.id
                )
            },
            onNameClick = {
                sortByName()
                showMessageByToast("Сортировка по названию")
            },
            onSpecialCodeClick = {
                sortBySpecialCode()
                showMessageByToast("Сортировка по коду направления")
            },
            onSpecialClick = {
                sortBySpecial()
                showMessageByToast("Сортировка по названию направления")
            },
            onProfileClick = {
                sortByProfile()
                showMessageByToast("Сортировка по профилю")
            },
            onStudentsClick = { group ->
                navigator?.navigateToStudents(group)
            }
        )
    }

    private fun sortByName() {
        adapter.items = adapter.items.sortedBy { it.numberGroup }
    }

    private fun sortBySpecialCode() {
        adapter.items = adapter.items.sortedBy { it.specialCode }
    }

    private fun sortBySpecial() {
        adapter.items = adapter.items.sortedBy { it.special }
    }

    private fun sortByProfile() {
        adapter.items = adapter.items.sortedBy { it.profile }
    }

    private val initParams: GroupListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_groups_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGroups.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.facultyName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToGroupCreateEdit(initParams.facultyId)
        }

        viewModel = ViewModelProvider(this).get(GroupListViewModel::class.java)
        viewModel.getGroups(initParams.facultyId)
        viewModel.groupsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(groupFulls: List<Group>) {
        adapter.items = groupFulls.sortedBy { it.numberGroup }
    }

    companion object {
        fun newInstance(groupListFragmentInitParams: GroupListFragmentInitParams): GroupListFragment =
            GroupListFragment().provideInitParams(groupListFragmentInitParams) as GroupListFragment
    }
}
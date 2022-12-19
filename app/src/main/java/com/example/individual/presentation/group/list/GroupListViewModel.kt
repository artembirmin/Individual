package com.example.individual.presentation.group.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.GroupRepository
import com.example.individual.model.Group
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class GroupListViewModel : ViewModel() {
    val groupsLiveData = MutableLiveData<List<Group>>()
    private val groupRepository = GroupRepository.getInstance()

    fun getGroups(facultyId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            groupRepository.observeGroups(facultyId).collect {
                groupsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            groupRepository.refreshGroups()
        }
    }
}
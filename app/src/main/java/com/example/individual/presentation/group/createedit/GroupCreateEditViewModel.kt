package com.example.individual.presentation.group.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.GroupRepository
import com.example.individual.model.Group
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class GroupCreateEditViewModel : ViewModel() {
    val groupLiveData = MutableLiveData<Group?>()
    private val groupRepository = GroupRepository.getInstance()

    fun getGroup(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            groupLiveData.postValue(groupRepository.getGroupById(id))
        }
    }

    fun saveGroup(newGroup: Group) {
        viewModelScope.launch(defaultErrorHandler) {
            groupLiveData.value?.let { oldGroup ->
                groupRepository.update(
                    newGroup.copy(
                        id = oldGroup.id,
                        budgetStudentsCount = oldGroup.budgetStudentsCount,
                        commerceStudentsCount = oldGroup.commerceStudentsCount,
                    )
                )
            } ?: groupRepository.add(newGroup)
        }
    }

    fun deleteGroup() {
        groupLiveData.value?.let { group ->
            viewModelScope.launch(defaultErrorHandler) {
                groupRepository.delete(group)
            }
        }
    }
}
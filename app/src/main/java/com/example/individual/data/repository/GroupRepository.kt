package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Group
import kotlinx.coroutines.flow.Flow

class GroupRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val groupDao = DatabaseProvider.get().getGroupDao()

    fun observeGroups(facultyId: Long): Flow<List<Group>> {
        return groupDao.getGroups(facultyId)
    }

    suspend fun refreshGroups() {
        val groups = individualApi.getGroups().map { it }
        groupDao.deleteAll()
        groupDao.insertAll(groups)
    }

    suspend fun updateGroups() {
        val groups = individualApi.getGroups().map { it }
        groupDao.updateAll(groups)
    }

    suspend fun add(group: Group) {
        val groupFromServer =
            individualApi.addGroup(group)
        groupDao.insert(groupFromServer)
    }

    suspend fun update(group: Group) {
        val groupFromServer =
            individualApi.updateGroup(group.id, group)

        groupDao.insert(groupFromServer)
    }

    suspend fun delete(group: Group) {
        individualApi.deleteGroup(group.id)
        groupDao.delete(group)
    }

    suspend fun getGroupById(id: Long): Group {
        return groupDao.getById(id)
    }

    companion object {
        private var INSTANCE: GroupRepository? = null
        fun getInstance(): GroupRepository {
            if (INSTANCE == null) {
                INSTANCE = GroupRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
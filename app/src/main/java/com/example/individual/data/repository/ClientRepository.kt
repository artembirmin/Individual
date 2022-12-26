package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository {
    private val individualApi = NetworkProvider.get().individualApi
    private val clientDao = DatabaseProvider.get().getClientDao()

    fun observeClients(): Flow<List<Client>> {
        return clientDao.getClients()
    }

    suspend fun getClientById(id: Long): Client {
        return clientDao.getClientById(id)
    }

    suspend fun refreshClients() {
        val clients = individualApi.getClients()
        clientDao.deleteAll()
        clientDao.insertAll(clients)
    }

    suspend fun add(client: Client) {
        val clientFromServer = individualApi.addClient(client)
        clientDao.insert(clientFromServer)
    }

    suspend fun update(client: Client) {
        val clientFromServer = individualApi.updateClient(client.id, client)
        clientDao.insert(clientFromServer)
    }

    suspend fun delete(client: Client) {
        individualApi.deleteClient(client.id)
        clientDao.deleteClient(client)
    }

    companion object {
        private var INSTANCE: ClientRepository? = null
        fun getInstance(): ClientRepository {
            if (INSTANCE == null) {
                INSTANCE = ClientRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
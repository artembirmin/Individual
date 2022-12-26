package com.example.individual.presentation.client.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.ClientRepository
import com.example.individual.model.Client
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class ClientCreateEditViewModel : ViewModel() {
    val clientLiveData = MutableLiveData<Client?>()
    private val clientRepository = ClientRepository.getInstance()

    fun getClient(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            clientLiveData.postValue(clientRepository.getClientById(id))
        }
    }

    fun saveClient(newClient: Client) {
        viewModelScope.launch(defaultErrorHandler) {
            clientLiveData.value?.let { oldClient ->
                clientRepository.update(newClient.copy(id = oldClient.id))
            } ?: clientRepository.add(newClient)
        }
    }

    fun deleteClient() {
        clientLiveData.value?.let { client ->
            viewModelScope.launch(defaultErrorHandler) {
                clientRepository.delete(client)
            }
        }
    }
}
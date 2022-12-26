package com.example.individual.presentation.client.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.ClientRepository
import com.example.individual.model.Client
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class ClientListViewModel : ViewModel() {
    val clientsLiveData = MutableLiveData<List<Client>>()
    private val clientRepository = ClientRepository.getInstance()

    fun getClients() {
        viewModelScope.launch(defaultErrorHandler) {
            clientRepository.observeClients().collect {
                clientsLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) { clientRepository.refreshClients() }
    }

    fun onDeleteClientClick(client: Client) {
        viewModelScope.launch(defaultErrorHandler) {
            clientRepository.delete(client)
        }
    }
}
package com.example.individual.presentation.order.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.OrderRepository
import com.example.individual.model.OrderShort
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class OrderListViewModel : ViewModel() {
    val ordersLiveData = MutableLiveData<List<OrderShort>>()
    private val orderRepository = OrderRepository.getInstance()

    fun getOrders(clientId: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            orderRepository.observeOrders(clientId).collect {
                ordersLiveData.postValue(it)
            }
        }
        viewModelScope.launch(defaultErrorHandler) {
            orderRepository.refreshOrders()
        }
    }
}
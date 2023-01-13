package com.example.individual.presentation.order.createedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.individual.data.repository.OrderRepository
import com.example.individual.model.OrderFull
import com.example.individual.utils.defaultErrorHandler
import kotlinx.coroutines.launch

class OrderCreateEditViewModel : ViewModel() {
    val orderLiveData = MutableLiveData<OrderFull?>()
    private val orderRepository = OrderRepository.getInstance()

    fun getOrder(id: Long) {
        viewModelScope.launch(defaultErrorHandler) {
            orderLiveData.postValue(orderRepository.getOrderById(id))
        }
    }

    fun saveOrder(newOrder: OrderFull) {
        viewModelScope.launch(defaultErrorHandler) {
            val oldOrder = orderLiveData.value
            if (oldOrder != null) {
                orderRepository.update(newOrder.copy(id = oldOrder.id))
            } else {
                orderRepository.add(newOrder)
            }
        }
    }

    fun deleteOrder() {
        val order = orderLiveData.value
        if (order != null) {
            viewModelScope.launch(defaultErrorHandler) {
                orderRepository.delete(order)
            }
        }
    }
}
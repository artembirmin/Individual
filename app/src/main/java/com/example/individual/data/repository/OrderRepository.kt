package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.OrderFull
import com.example.individual.model.OrderShort
import kotlinx.coroutines.flow.Flow

class OrderRepository {

    private val individualApi = NetworkProvider.get().individualApi
    private val orderDao = DatabaseProvider.get().getOrderDao()

    fun observeOrders(clientId: Long): Flow<List<OrderShort>> {
        return orderDao.getOrders(clientId)
    }

    suspend fun refreshOrders() {
        val clients = individualApi.getOrders().map { it.toOrderFull() }
        orderDao.deleteAll()
        orderDao.insertAll(clients)
    }

    suspend fun add(order: OrderFull) {
        val clientFromServer = individualApi.addOrder(order.toServerModel()).toOrderFull()
        orderDao.insert(clientFromServer)
    }

    suspend fun update(order: OrderFull) {
        val clientFromServer =
            individualApi.updateOrder(order.id, order.toServerModel()).toOrderFull()
        orderDao.insert(clientFromServer)
    }

    suspend fun delete(orderFull: OrderFull) {
        individualApi.deleteOrder(orderFull.id)
        orderDao.delete(orderFull)
    }

    suspend fun getOrderById(id: Long): OrderFull {
        return orderDao.getById(id)
    }

    companion object {
        private var INSTANCE: OrderRepository? = null
        fun getInstance(): OrderRepository {
            if (INSTANCE == null) {
                INSTANCE = OrderRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
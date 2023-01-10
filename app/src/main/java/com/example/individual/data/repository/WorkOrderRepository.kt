package com.example.individual.data.repository

import com.example.individual.data.database.DatabaseProvider
import com.example.individual.data.network.NetworkProvider
import com.example.individual.model.WorkOrderFull
import com.example.individual.model.WorkOrderShort
import kotlinx.coroutines.flow.Flow

class WorkOrderRepository {

    private val individualApi = NetworkProvider.get().api
    private val workOrderDao = DatabaseProvider.get().getWorkOrderDao()

    fun observeWorkOrders(serviceStationId: Long): Flow<List<WorkOrderShort>> {
        return workOrderDao.getWorkOrders(serviceStationId)
    }

    suspend fun refreshWorkOrders() {
        val serviceStations = individualApi.getWorkOrders().map { it.toWorkOrderFull() }
        workOrderDao.deleteAll()
        workOrderDao.insertAll(serviceStations)
    }

    suspend fun add(workOrder: WorkOrderFull) {
        val serviceStationFromServer =
            individualApi.addWorkOrder(workOrder.toServerModel()).toWorkOrderFull()
        workOrderDao.insert(serviceStationFromServer)
    }

    suspend fun update(workOrder: WorkOrderFull) {
        val serviceStationFromServer =
            individualApi.updateWorkOrder(workOrder.id, workOrder.toServerModel()).toWorkOrderFull()
        workOrderDao.insert(serviceStationFromServer)
    }

    suspend fun delete(workOrderFull: WorkOrderFull) {
        val response = individualApi.deleteWorkOrder(workOrderFull.id)
        if (response.isSuccessful) {
            workOrderDao.delete(workOrderFull)
        }
    }

    suspend fun getWorkOrderById(id: Long): WorkOrderFull {
        return workOrderDao.getById(id)
    }

    companion object {
        private var INSTANCE: WorkOrderRepository? = null
        fun getInstance(): WorkOrderRepository {
            if (INSTANCE == null) {
                INSTANCE = WorkOrderRepository()
            }
            return INSTANCE ?: throw IllegalStateException("CageRepository is not init")
        }
    }
}
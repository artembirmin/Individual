package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.OrderFull
import com.example.individual.model.OrderShort
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE  clientId = (:clientId)")
    fun getOrders(clientId: Long): Flow<List<OrderShort>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getById(id: Long): OrderFull

    @Update
    suspend fun update(orderFull: OrderFull)

    @Update
    suspend fun updateAll(ist: List<OrderFull>)

    @Delete
    suspend fun delete(orderFull: OrderFull)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderFull: OrderFull)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<OrderFull>)

    companion object {
        private const val TABLE_NAME = OrderFull.TABLE_NAME
    }
}
package com.example.individual.data.database

import androidx.room.*
import com.example.individual.model.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getClients(): Flow<List<Client>>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = (:id)")
    suspend fun getClientById(id: Long): Client

    @Update
    suspend fun updateClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(client: Client)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Client>)

    companion object {
        private const val TABLE_NAME = Client.TABLE_NAME
    }
}
package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getItems(): Flow<List<BusSchedule>>
    fun getItem(id: Int): Flow<BusSchedule?>
    suspend fun insertItem(item: BusSchedule)
    suspend fun updateItem(item: BusSchedule)
    suspend fun deleteItem(item: BusSchedule)
}
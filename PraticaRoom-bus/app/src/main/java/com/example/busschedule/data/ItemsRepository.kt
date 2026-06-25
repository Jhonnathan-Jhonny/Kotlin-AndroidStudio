package com.example.busschedule.data

interface ItemsRepository {
    fun getItems(): List<BusSchedule>
    fun getItem(id: Int): BusSchedule
    suspend fun insertItem(item: BusSchedule)
    suspend fun updateItem(item: BusSchedule)
    suspend fun deleteItem(item: BusSchedule)
}
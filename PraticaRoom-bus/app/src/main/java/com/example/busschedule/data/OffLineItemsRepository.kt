//package com.example.busschedule.data
//
//import kotlinx.coroutines.flow.Flow
//
//class OffLineItemsRepository(
//    private val busDao: BusDao
//) : ItemsRepository {
//    override fun getItems(): Flow<List<BusSchedule>> = busDao.getAll()
//
//    override fun getItem(id: Int): Flow<BusSchedule?> = busDao.getById(id)
//
//    override suspend fun insertItem(item: BusSchedule) = busDao.insert(item)
//
//    override suspend fun updateItem(item: BusSchedule) = busDao.update(item)
//
//    override suspend fun deleteItem(item: BusSchedule) = busDao.delete(item)
//
//}
package com.example.fly_practice_roomdatastore.data

class ItemRepository(
    private val airportDao: ItemDao
) {
    suspend fun searchAirports(query: String): List<Item> {
        return airportDao.searchAirports(query)
    }

    suspend fun getFavoriteAirports(codes: List<String>): List<Item> {
        return airportDao.getFavoriteAirports(codes)
    }
}
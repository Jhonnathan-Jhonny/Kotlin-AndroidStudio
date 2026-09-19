package com.example.fly_practice_roomdatastore.data

import androidx.room3.Dao
import androidx.room3.Query

@Dao
interface ItemDao {

    @Query(
"SELECT * FROM airport " +
        "WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%' " +
        "ORDER BY passengers DESC"
    )
    suspend fun searchAirports(query: String): List<Item>

    @Query("""
    SELECT * FROM airport
    WHERE iata_code IN (:codes)
""")
    suspend fun getFavoriteAirports(codes: List<String>): List<Item>
}
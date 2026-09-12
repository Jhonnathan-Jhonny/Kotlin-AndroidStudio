package com.example.fly_practice_roomdatastore.data

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "airport")
data class Item(
    @PrimaryKey
    val id: Int,

    val name: String,

    @ColumnInfo(name = "iata_code")
    val iataCode: String,

    val passengers: Int
)
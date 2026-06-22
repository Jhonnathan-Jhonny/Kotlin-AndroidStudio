package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BusDao {

    @Insert
    suspend fun insert(busSchedule: BusSchedule)

    @Update
    suspend fun update(busSchedule: BusSchedule)

    @Delete
    suspend fun delete(busSchedule: BusSchedule)

    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getById(id: Int): Flow<BusSchedule>

    @Query("SELECT * FROM schedule ORDER BY arrivalTimeInMillis ASC")
    fun getAll(): Flow<List<BusSchedule>>

    @Query("SELECT * FROM schedule WHERE stopName = :stopName ORDER BY arrivalTimeInMillis ASC")
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>
}
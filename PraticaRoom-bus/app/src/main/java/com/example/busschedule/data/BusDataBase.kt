package com.example.busschedule.data

import androidx.room.Database


@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusDataBase {
}
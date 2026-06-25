package com.example.busschedule

import android.app.Application
import com.example.busschedule.data.BusDataBase

class BusApplication: Application() {
    val database: BusDataBase by lazy { BusDataBase.getDatabase(this) }
}
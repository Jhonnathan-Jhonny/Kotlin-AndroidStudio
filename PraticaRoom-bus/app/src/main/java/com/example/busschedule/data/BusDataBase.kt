package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusDataBase : RoomDatabase() {

    abstract fun busDao(): BusDao

    //O companion object serve para criar membros (variáveis e funções)
    // que pertencem à própria classe, e não a instâncias específicas dela.
    companion object {
        @Volatile
        private var INSTANCE: BusDataBase? = null

        fun getDatabase(context: Context): BusDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    klass = BusDataBase::class.java,
                    name = "bus_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }
}
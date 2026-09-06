package com.example.fly_practice_roomdatastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.fly_practice_roomdatastore.data.UserPreferencesRepository


private const val TAG = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = TAG
)


class FlyReleaseApplication : Application() {
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}
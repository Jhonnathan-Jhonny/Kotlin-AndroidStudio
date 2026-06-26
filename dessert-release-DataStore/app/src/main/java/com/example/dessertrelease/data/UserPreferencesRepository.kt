package com.example.dessertrelease.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
){
    private companion object {
        val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveLayoutPreference(isLinearLayoutManager: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
        }
    }

    val isLinearLayoutManager: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException){
                Log.e(TAG,"Error reading preferences",it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map {
            it[IS_LINEAR_LAYOUT_MANAGER] ?: true
        }
}
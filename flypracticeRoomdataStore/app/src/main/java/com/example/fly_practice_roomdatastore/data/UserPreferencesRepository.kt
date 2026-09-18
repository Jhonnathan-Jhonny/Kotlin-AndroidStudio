package com.example.fly_practice_roomdatastore.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        // Chave utilizada para armazenar os códigos IATA
        // dos aeroportos favoritos.
        val FAVORITE_AIRPORTS =
            stringSetPreferencesKey("favorite_airports")

        const val TAG = "UserPreferencesRepo"
    }

    // Adicionar ou remover um aeroporto dos favoritos
    suspend fun toggleFavorite(iataCode: String) {
        dataStore.edit { preferences ->

            val currentFavorites =
                preferences[FAVORITE_AIRPORTS] ?: emptySet()

            preferences[FAVORITE_AIRPORTS] =
                if (iataCode in currentFavorites) {
                    currentFavorites - iataCode
                } else {
                    currentFavorites + iataCode
                }
        }
    }

    // Ler os aeroportos favoritos
    val favoriteAirports: Flow<Set<String>> =
        dataStore.data
            .catch {
                if (it is IOException) {
                    Log.e(TAG, "Error reading preferences.", it)
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                preferences[FAVORITE_AIRPORTS] ?: emptySet()
            }
}
package com.example.fly_practice_roomdatastore.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        //Essa chave é usada para acessar um valor booleano que indica se o layout linear precisa ser mostrado.
        val IS_LINEAR_LAYOUT = booleanPreferencesKey("is_linear_layout")
        //Lidando com falhas: TAG é usada para gerar registros.
        const val TAG = "UserPreferencesRepo"
    }

    //Gravar no DataStore
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean, context: Context) {
        dataStore.edit { preferences ->
            preferences[IS_LINEAR_LAYOUT] = isLinearLayoutManager
        }
    }

    //Ler do DataStore
    //Flow: Joga dados do BD como uma torneira ligada joga agua ao mesmo tempo que o app roda(corrotinas)
    val isLinearLayoutManager: Flow<Boolean> = dataStore.data
        //Tratando erros.
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        //Map: converte o Flow<preferences> em boolean
        .map { preferences ->
            preferences[IS_LINEAR_LAYOUT] ?: true
        }
}
package com.example.fly_practice_roomdatastore.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fly_practice_roomdatastore.FlyReleaseApplication
import com.example.fly_practice_roomdatastore.data.Item
import com.example.fly_practice_roomdatastore.data.ItemRepository
import com.example.fly_practice_roomdatastore.data.UserPreferencesRepository
import kotlinx.coroutines.launch


class HomeViewModel(
    private val itemRepository: ItemRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel()
{

    //Buscra no banco de daos
    var searchResults by mutableStateOf<List<Item>>(emptyList())
        private set

    fun searchAirports(query: String) {
        viewModelScope.launch {
            searchResults = itemRepository.searchAirports(query)
        }
    }


    //Fornecendo repositório ao viewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlyReleaseApplication)
                HomeViewModel(
                    itemRepository = application.itemRepository,
    application.userPreferencesRepository
                )
            }
        }
    }

    //Armazenar preferencias
}
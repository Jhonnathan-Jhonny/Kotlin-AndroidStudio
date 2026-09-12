package com.example.fly_practice_roomdatastore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fly_practice_roomdatastore.FlyReleaseApplication
import com.example.fly_practice_roomdatastore.data.UserPreferencesRepository


class HomeViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel()
{
    //Fornecendo repositório ao viewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlyReleaseApplication)
                HomeViewModel(
                    application.userPreferencesRepository
                )
            }
        }
    }
}
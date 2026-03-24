package com.example.marsphotos.network

sealed interface  MarsUiState {
    data class Success(val photos: String) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}
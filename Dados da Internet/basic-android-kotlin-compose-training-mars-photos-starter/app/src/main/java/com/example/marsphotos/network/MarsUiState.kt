package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto

sealed interface  MarsUiState {
    data class Success(val photos: List<MarsPhoto>) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}
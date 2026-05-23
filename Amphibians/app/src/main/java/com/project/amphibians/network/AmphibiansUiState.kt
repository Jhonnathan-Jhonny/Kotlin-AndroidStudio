package com.project.amphibians.network

import com.project.amphibians.model.AmphibiansModel

sealed interface AmphibiansUiState {
    data class Success(val photos: List<AmphibiansModel>) : AmphibiansUiState
    data object Error : AmphibiansUiState
    data object Loading : AmphibiansUiState
}
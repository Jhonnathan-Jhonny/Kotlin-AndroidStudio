package com.project.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.amphibians.data.AmphibiansRepository
import com.project.amphibians.network.AmphibiansUiState

class AmphibiansViewModel (
    private val amphibiansRepository: AmphibiansRepository
) : ViewModel() {

    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        TODO("Not yet implemented")
    }
}

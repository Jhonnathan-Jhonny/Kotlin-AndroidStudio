package com.project.amphibians.data

import com.project.amphibians.model.AmphibiansModel
import com.project.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansModel>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansModel> = amphibiansApiService.getAmphibians()
}
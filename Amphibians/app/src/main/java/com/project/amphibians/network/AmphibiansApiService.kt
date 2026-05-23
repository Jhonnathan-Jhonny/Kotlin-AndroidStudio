package com.project.amphibians.network

import com.project.amphibians.model.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibiansModel>
}

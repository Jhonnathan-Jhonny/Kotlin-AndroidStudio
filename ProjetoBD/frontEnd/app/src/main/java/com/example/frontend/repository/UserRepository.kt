package com.example.frontend.repository

import com.example.frontend.model.ApiService
import com.example.frontend.model.UserRequest
import com.example.frontend.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val BASE_URL = "http://10.0.0.169:8081"

    private val apiService: ApiService

    init {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun registerUser(name: String, email: String, password: String): Response<UserResponse> {
        val request = UserRequest(name, email, password)
        return apiService.registerUser(request)
    }
}

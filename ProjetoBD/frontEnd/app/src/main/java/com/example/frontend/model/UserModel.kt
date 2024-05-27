package com.example.frontend.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class UserRequest(
    val name: String,
    val email: String,
    val password: String
)

data class UserResponse(
    val id: String,
    val name: String,
    val email: String
)

interface ApiService {
    @POST("/user/register")
    suspend fun registerUser(@Body request: UserRequest): Response<UserResponse>
}
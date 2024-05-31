package com.example.frontend.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val password: String
){
    fun toUserFormUiState() = UserRequest(
        name = name,
        email = email,
        password = password
    )
}
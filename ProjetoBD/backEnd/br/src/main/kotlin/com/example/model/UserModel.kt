package com.example.model

import kotlinx.serialization.Serializable

@Serializable
class UserModel(
    val name: String,
    val email: String,
    val password: String
)
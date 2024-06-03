package com.example.frontend.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    var name: String,
    var email: String,
    var password: String
)
package br.com.jhonnathan

import io.ktor.server.auth.*

data class UserSession(val userId: String) : Principal

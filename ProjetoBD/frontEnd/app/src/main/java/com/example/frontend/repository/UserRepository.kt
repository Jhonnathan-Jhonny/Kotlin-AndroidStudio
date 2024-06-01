package com.example.frontend.repository

import com.example.frontend.model.UserRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class User(val name: String, val email: String, val password: String)

@Serializable
data class RegistrationResponse(val status: Boolean, val message: String, val user: User? = null)

class UserRepository {

    private fun httpClient(): HttpClient {
        return HttpClient(Android) {
            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }
            install(Logging) {
                logger = io.ktor.client.plugins.logging.Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    suspend fun registerUser(name: String, email: String, password: String): RegistrationResponse {
        val apiService = httpClient()
        val request = UserRequest(name, email, password)
        val response: HttpResponse = apiService.post("http://10.0.0.169:8081/user/register") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(request)
        }

        return if (response.status == HttpStatusCode.OK) {
            RegistrationResponse(true, "Created user successfully", User(name, email, password))
        } else {
            val errorMessage = response.bodyAsText()
            RegistrationResponse(false, errorMessage)
        }
    }

    suspend fun loginUser(email: String, password: String): RegistrationResponse {
        val apiService = httpClient()
        val request = UserRequest("", email, password)
        val response: HttpResponse = apiService.post("http://10.0.0.169:8081/user/login") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(request)
        }
        return if (response.status == HttpStatusCode.OK) {
            val user = response.body<User>()
            RegistrationResponse(true, "user successfully logged in", user)
        } else {
            val errorMessage = response.bodyAsText()
            RegistrationResponse(false, errorMessage)
        }
    }
    suspend fun getUserInfo(): User {
        val apiService = httpClient()
        val response =  apiService.get("http://10.0.0.169:8081/user/me")
        return response.body()
    }
}
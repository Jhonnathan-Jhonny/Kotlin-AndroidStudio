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
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class User(var name: String, var email: String, var password: String)

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

    suspend fun getUserInfo(): UserRequest {
        val apiService = httpClient()
        val response = apiService.get("http://10.0.0.169:8081/user/me") {
            contentType(io.ktor.http.ContentType.Application.Json)
        }
        return response.body()
    }

    suspend fun deleteUser(name:String): HttpStatusCode {
        val apiService = httpClient()
        val response = apiService.delete("http://10.0.0.169:8081/user/delete/$name") {
            contentType(io.ktor.http.ContentType.Application.Json)
        }
        return response.status
    }
    suspend fun logOutUser(): HttpStatusCode {
        val apiService = httpClient()
        val response = apiService.post("http://10.0.0.169:8081/user/logout") {
            contentType(io.ktor.http.ContentType.Application.Json)
        }
        return response.status
    }

    suspend fun editUser(namePrevious:String,name: String, email: String): HttpStatusCode {
        val apiService = httpClient()
        val request = UserRequest(name, email, "")
        val response = apiService.put("http://10.0.0.169:8081/user/edit/$namePrevious") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(request)
        }
        return response.status
    }
}



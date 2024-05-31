package com.example.frontend.repository

import com.example.frontend.model.UserRequest
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class RegistrationResponse(val status: Boolean, val message: String)

class UserRepository {

    private fun httpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
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
            RegistrationResponse(true, "Created user successfully")
        } else {
            val errorMessage = response.bodyAsText()
            RegistrationResponse(false, errorMessage)
        }
    }
}
//    suspend fun loginUser(email: String, password: String): Response<UserResponse> {
//        val request = UserRequest(name = "", email = email, password = password)
//        val response = apiService.loginUser(request)
//        Log.d("ApiResponse", "Request: $request")
//        Log.d("ApiResponse", "Response: ${response.raw()}")
//        return response
//    }
//
//    suspend fun searchUser(name: String): Boolean {
//        val request = UserRequest(name, "", "")
//        val response = apiService.searchUser(request)
//        Log.d("ApiResponse", "Request: $request")
//        Log.d("ApiResponse", "Response: ${response.raw()}")
//        return response.isSuccessful
//    }
//
//}

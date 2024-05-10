package com.example.plugins

import com.example.model.UserModel
import com.example.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = UserRepository()
    routing {
        get("/userList"){
            call.respond(repository.users)
        }
        post("/resgisterUser") {
            val user = call.receive<UserModel>()
            repository.save(user)
            call.respondText ("User resgistered successfully!", status = HttpStatusCode.Created)
        }

        post("/loginUser") {
            val user = call.receive<UserModel>()
            val existingUser = repository.findByUsername(user.name)

            if (existingUser != null && existingUser.password == user.password) {
                call.respondText("Login successful!", status = HttpStatusCode.OK)
            } else {
                call.respondText("Invalid username or password", status = HttpStatusCode.Unauthorized)
            }
        }


    }
}

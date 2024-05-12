package br.com.jhonnathan.application.routs

import br.com.jhonnathan.application.request.UserRequest
import br.com.jhonnathan.application.request.toDomain
import br.com.jhonnathan.domain.ports.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val repository by inject<UserRepository>()
    route("/user") {
        post("/register") {
            val user = call.receive<UserRequest>()
            val insertedId = repository.save(user.toDomain())
            call.respond(HttpStatusCode.Created, "Created user with id $insertedId")
        }

        delete("/delete/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                text = "Missing user id",
                status = HttpStatusCode.BadRequest
            )
            val delete: Long = repository.delete(ObjectId(id))
            if (delete == 1L) {
                return@delete call.respondText("User Deleted successfully", status = HttpStatusCode.OK)
            }
            return@delete call.respondText("User not found", status = HttpStatusCode.NotFound)
        }

        get("/search/{id?}") {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                return@get call.respondText(
                    text = "Missing id",
                    status = HttpStatusCode.BadRequest
                )
            }
            repository.findById(ObjectId(id))?.let {
                call.respond(it.toResponse())
            } ?: call.respondText("No records found for id $id")
        }

        patch("/edit/{id?}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                text = "Missing fitness id",
                status = HttpStatusCode.BadRequest
            )
            val updated = repository.updateUser(ObjectId(id), call.receive())
            call.respondText(
                text = if (updated == 1L) "User updated successfully" else "User not found",
                status = if (updated == 1L) HttpStatusCode.OK else HttpStatusCode.NotFound
            )
        }
    }
}
package br.com.jhonnathan.application.routs

import br.com.jhonnathan.UserSession
import br.com.jhonnathan.application.request.UserRequest
import br.com.jhonnathan.application.request.toDomain
import br.com.jhonnathan.domain.entity.User
import br.com.jhonnathan.domain.ports.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.koin.ktor.ext.inject
import org.mindrot.jbcrypt.BCrypt



fun Route.userRoutes() {
    val repository by inject<UserRepository>()
    var infoUserLogged = UserRequest("","","")

    route("/user") {

        get("/me") {
            return@get call.respond(infoUserLogged)
        }

        post("/register") {
            val user = call.receive<UserRequest>()
            val userExist = repository.findByName(user.name)
            if(userExist != null){
                return@post call.respond(HttpStatusCode.Conflict,"User name already exists")
            }
            user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
            val insertedId = repository.save(user.toDomain())
            return@post call.respond(HttpStatusCode.OK, "Created user with id $insertedId")
        }

        post("/login") {
            val user = call.receive<UserRequest>()
            val userInDb = repository.findByEmail(user.email)
            if (userInDb == null || !BCrypt.checkpw(user.password, userInDb.password)) {
                return@post call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
            infoUserLogged = UserRequest(userInDb.name, userInDb.email,userInDb.password)
            call.sessions.set(UserSession(userInDb.id.toString()))
            return@post call.respond(HttpStatusCode.OK, userInDb)
        }


        post("/logout") {
            try {
                call.sessions.clear<UserSession>()
                call.respondText("User logged out successfully", status = HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respondText("Failed to logout: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }

        delete("/delete/{name?}") {
            val name = call.parameters["name"] ?: return@delete call.respondText(
                text = "Missing user name",
                status = HttpStatusCode.BadRequest
            )
            val delete: Long = repository.delete(name)
            if (delete == 1L) {
                return@delete call.respondText("User Deleted successfully", status = HttpStatusCode.OK)
            }
            return@delete call.respondText("User not found", status = HttpStatusCode.NotFound)
        }

        get("/search/{name?}") {
            val name = call.parameters["name"]
            if (name.isNullOrEmpty()) {
                return@get call.respondText(
                    text = "Missing id",
                    status = HttpStatusCode.BadRequest
                )
            }
            repository.findByName(name)?.let {
                call.respond(it.toResponse())
            } ?: call.respondText("No records found for name $name")
        }
//        put("/edit/{name?}") {
//            val name = call.parameters["name"].toString()
//            val user = call.receive<UserRequest>()
//            val userExist = repository.findByName(name)
//            if(userExist != null){
//                val updated = repository.updateUser(name,user.toDomain())
//                call.respondText(
//                    text = if (updated == 1L) "User updated successfully" else "User not found",
//                    status = if (updated == 1L) HttpStatusCode.OK else HttpStatusCode.NotFound
//                )
//            }
//            return@put call.respond(HttpStatusCode.BadRequest,"User name no exists")
//        }

        put("/edit/{name?}") {
            val name = call.parameters["name"] ?: return@put call.respondText(
                text = "Missing user name",
                status = HttpStatusCode.BadRequest
            )
            val updated = repository.updateUser(name, call.receive<User>())
            call.respondText(
                text = if (updated == 1L) "User updated successfully" else "User not found",
                status = if (updated == 1L) HttpStatusCode.OK else HttpStatusCode.NotFound
            )
        }

    }
}
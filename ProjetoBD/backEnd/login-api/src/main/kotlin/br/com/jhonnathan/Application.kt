package br.com.jhonnathan

import br.com.jhonnathan.application.routs.userRoutes
import br.com.jhonnathan.domain.ports.UserRepository
import br.com.jhonnathan.infrastructure.repository.UserRepositoryImpl
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.http.*
import io.ktor.serialization.gson.gson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.session
import io.ktor.server.response.*
import io.ktor.server.tomcat.EngineMain
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        gson {
        }
    }
    install(Sessions) {
        cookie<UserSession>("USER_SESSION")
    }
    install(Authentication) {
        session<UserSession>("auth-session") {
            validate { session ->
                if (session.userId.isNotEmpty()) session else null
            }
            challenge {
                call.respond(HttpStatusCode.Unauthorized, "Invalid session")
            }
        }
    }
    install(Koin) {
        slf4jLogger()
        modules(module {
            single { MongoClient.create(
                environment.config.propertyOrNull("ktor.mongo.uri")?.getString() ?: throw RuntimeException("Failed to access MongoDB URI.")
            ) }
            single { get<MongoClient>().getDatabase(environment.config.property("ktor.mongo.database").getString()) }
        }, module {
            single<UserRepository> { UserRepositoryImpl(get()) }
        })
    }
    routing {
        swaggerUI(path = "swagger-ui", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
        userRoutes()
    }
}

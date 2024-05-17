package br.com.jhonnathan.application.request

import br.com.jhonnathan.domain.entity.User
import org.bson.types.ObjectId

data class UserRequest(
    val name:String,
    val email:String,
    var password:String
)

fun UserRequest.toDomain(): User {
    return User(
        id = ObjectId(),
        name = name,
        email = email,
        password = password
    )
}
package br.com.jhonnathan.domain.entity

import br.com.jhonnathan.application.response.UserResponse
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User (
    @BsonId
    val id: ObjectId,
    var name:String,
    var email:String,
    var password:String
){
    fun toResponse() = UserResponse(
        id = id.toString(),
        name = name,
        email = email,
        password = password
    )
}
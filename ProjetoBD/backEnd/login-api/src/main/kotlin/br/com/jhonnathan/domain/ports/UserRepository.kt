package br.com.jhonnathan.domain.ports

import br.com.jhonnathan.application.request.UserRequest
import br.com.jhonnathan.application.response.UserResponse
import br.com.jhonnathan.domain.entity.User
import org.bson.types.ObjectId

interface UserRepository {
    suspend fun save(user: User): Boolean
    suspend fun findById(objectId: ObjectId): User?
    suspend fun delete(name: String): Long
    suspend fun updateUser(userName: String, user: User): Long
    suspend fun findByName(userName: String): User?
    suspend fun findByEmail(email: String): User?
}
package br.com.jhonnathan.domain.ports

import br.com.jhonnathan.domain.entity.User
import org.bson.types.ObjectId

interface UserRepository {
    suspend fun save(user: User): Boolean
    suspend fun findById(objectId: ObjectId): User?
    suspend fun delete(objectId: ObjectId): Long
    suspend fun updateUser(objectId: ObjectId, user: User): Long
}
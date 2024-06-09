package br.com.jhonnathan.infrastructure.repository

import br.com.jhonnathan.domain.entity.User
import br.com.jhonnathan.domain.ports.UserRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import kotlinx.coroutines.flow.firstOrNull
import org.bson.conversions.Bson
import org.bson.types.ObjectId


class UserRepositoryImpl(
private val mongoDatabase: MongoDatabase
): UserRepository {

    companion object{
        const val USER_COLLECTION = "user"
    }

    override suspend fun save(user: User): Boolean {
        try {
            val collection = mongoDatabase.getCollection<User>(USER_COLLECTION).insertOne(user)
            collection.insertedId
            return true
        } catch (e: MongoException) {
            System.err.println("Unable to insert due to an error: $e")
        }
        return false
    }

    override suspend fun findById(objectId: ObjectId): User? =
    mongoDatabase.getCollection<User>(USER_COLLECTION).withDocumentClass<User>()
    .find(Filters.eq("_id", objectId))
    .firstOrNull()

    override suspend fun delete(name: String): Long {
        try {
            val result = mongoDatabase.getCollection<User>(USER_COLLECTION).deleteOne(Filters.eq("name", name))
            return result.deletedCount
        } catch (e: MongoException) {
            System.err.println("Unable to delete due to an error: $e")
        }
        return 0
    }

    override suspend fun updateUser(userName: String, user: User): Boolean {
        try {
            val query = Filters.eq("name", userName)
            val updates = mutableListOf<Bson>()

            if (user.name.isNotEmpty() && userName != user.name) {
                updates.add(Updates.set(User::name.name, user.name))
            }
            if (user.email.isNotEmpty()) {
                updates.add(Updates.set(User::email.name, user.email))
            }
            if (user.password.isNotEmpty()) {
                updates.add(Updates.set(User::password.name, user.password))
            }


            if (updates.isNotEmpty()) {
                val combinedUpdates = Updates.combine(updates)
                val options = UpdateOptions().upsert(false)
                val result = mongoDatabase.getCollection<User>(USER_COLLECTION)
                    .updateOne(query, combinedUpdates, options)
                return result.matchedCount > 0
            }
        } catch (e: MongoException) {
            System.err.println("Unable to update due to an error: $e")
        }
        return false
    }


    override suspend fun findByName(userName: String): User? =
        mongoDatabase.getCollection<User>(USER_COLLECTION).withDocumentClass<User>()
            .find(Filters.eq("name", userName))
            .firstOrNull()

    override suspend fun findByEmail(email: String): User? =
        mongoDatabase.getCollection<User>(USER_COLLECTION)
        .withDocumentClass<User>()
        .find(Filters.eq("email", email))
        .firstOrNull()
}


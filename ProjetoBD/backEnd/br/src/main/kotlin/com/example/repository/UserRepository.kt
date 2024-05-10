package com.example.repository

import com.example.model.UserModel
import kotlinx.serialization.Serializer


class UserRepository {

    val users get() = _users.toList()

    fun save(user: UserModel) = _users.add(user)
    fun findByUsername(username: String): UserModel? {
        return  _users.find { user -> user.name == username }
    }

    companion object{
        private  val _users = mutableListOf<UserModel>()
    }
}
package com.sane4ek.aniroll._database.room.repository

import com.sane4ek.aniroll._database.room.entities.User

interface UserRepository {
//    val allUsers: ArrayList<User>

    suspend fun loginUser(email: String, password: String) : User?

    suspend fun addUser(user: User)

    suspend fun getNickname(nickName: String) : User?

    suspend fun getUserEmail(email: String) : User?
}
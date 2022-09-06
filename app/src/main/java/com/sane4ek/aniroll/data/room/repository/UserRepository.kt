package com.sane4ek.aniroll.data.room.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sane4ek.aniroll.data.room.entities.User

interface UserRepository {
//    val allUsers: ArrayList<User>

    suspend fun loginUser(email: String, password: String) : User

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User, onSuccess:() -> Unit)
}
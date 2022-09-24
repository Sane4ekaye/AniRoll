package com.sane4ek.aniroll._database.room.dao

import androidx.room.*
import com.sane4ek.aniroll._database.room.entities.User

@Dao
interface UserDao {

    //anime_user_table

    @Query("SELECT * FROM anime_user_table WHERE email = :email and password = :password")
    suspend fun login(email: String, password: String) : User?

    @Insert
    suspend fun add(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM anime_user_table WHERE nickname LIKE :nickName")
    suspend fun getNickname(nickName: String): User?

    @Query("SELECT * FROM anime_user_table WHERE email LIKE :email")
    suspend fun getUserEmail(email: String): User?
}
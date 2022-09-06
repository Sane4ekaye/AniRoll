package com.sane4ek.aniroll.data.room.dao

import androidx.room.*
import com.sane4ek.aniroll.data.room.entities.User

@Dao
interface UserDao {

    //anime_user_table

    @Query("SELECT * FROM anime_user_table WHERE email = :email and password = :password")
    suspend fun login(email: String, password: String) : User

    @Insert
    suspend fun add(user: User)

    @Delete
    fun delete(user: User)
}
package com.sane4ek.aniroll.data.room.repository

import com.sane4ek.aniroll.data.room.dao.UserDao
import com.sane4ek.aniroll.data.room.entities.User

class UserRealization(private val userDao: UserDao) : UserRepository {

    override suspend fun loginUser(email: String, password: String): User {
        return userDao.login(email, password)
    }
//    override val allUsers: ArrayList<User>
//        get() = userDao.getAllUsers()

    override suspend fun addUser(user: User) {
        userDao.add(user)
    }

    override suspend fun deleteUser(user: User, onSuccess: () -> Unit) {
        userDao.delete(user)
        onSuccess()
    }
}
package com.sane4ek.aniroll._database.room.repository

import com.sane4ek.aniroll._database.room.dao.UserDao
import com.sane4ek.aniroll._database.room.entities.User

class UserRealization(private val userDao: UserDao) : UserRepository {

    override suspend fun loginUser(email: String, password: String): User? {
        return userDao.login(email, password)
    }
//    override val allUsers: ArrayList<User>
//        get() = userDao.getAllUsers()

    override suspend fun addUser(user: User) {
        userDao.add(user)
    }

    override suspend fun getNickname(nickName: String): User? {
        return userDao.getNickname(nickName)
    }

    override suspend fun getUserEmail(email: String): User? {
        return userDao.getUserEmail(email)
    }
}
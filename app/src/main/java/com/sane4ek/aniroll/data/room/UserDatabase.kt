package com.sane4ek.aniroll.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sane4ek.aniroll.data.room.dao.UserDao
import com.sane4ek.aniroll.data.room.entities.User

@Database(
    entities = [User::class],
    version = 1
)

abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

    companion object {
        private var database: UserDatabase ?= null

        @Synchronized
        fun getInstance(context: Context) : UserDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, UserDatabase::class.java, "db").build()
                database as UserDatabase
            } else {
                database as UserDatabase
            }
         }
    }
}
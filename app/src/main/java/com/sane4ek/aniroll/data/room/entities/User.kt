package com.sane4ek.aniroll.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "anime_user_table")

class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var nickname: String = "",

    @ColumnInfo
    var email: String = "",

    @ColumnInfo
    var password: String = "",
) : Serializable
package com.sane4ek.aniroll.utils

import android.text.TextUtils
import com.sane4ek.aniroll.data.room.repository.UserRepository

lateinit var USER_REPOSITORY: UserRepository

const val PREFS_USER = "user"

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
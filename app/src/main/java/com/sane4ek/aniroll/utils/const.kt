package com.sane4ek.aniroll.utils

import android.text.TextUtils
import com.sane4ek.aniroll._database.room.repository.UserRepository

lateinit var USER_REPOSITORY: UserRepository

const val PREFS_USER = "user"
const val PREFS_FIRST_VISIT = "firstvisit"

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
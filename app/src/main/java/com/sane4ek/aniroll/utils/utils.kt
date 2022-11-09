package com.sane4ek.aniroll.utils

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import com.sane4ek.aniroll._database.room.repository.UserRepository
import com.sane4ek.aniroll.core.data.AppDataModel

lateinit var USER_REPOSITORY: UserRepository

const val PREFS_USER = "user"
const val PREFS_FIRST_VISIT = "firstvisit"
const val PREFS_APP_DATA = "appdata"

const val anilistUrl = "https://graphql.anilist.co/"

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}
package com.sane4ek.aniroll.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sane4ek.aniroll.data.room.entities.User

object SharedPrefs {

    private const val APP_PREFERENCES = "anirollprefs"

    fun saveUser(key: String?, value: User?, context: Context) {
        val sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    fun getUser(key: String, context: Context) : User? {
        val mPrefs: SharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val strObject = mPrefs.getString(key, "")
        val type = object : TypeToken<User?>() {}.type
        val myobject: User? = Gson().fromJson(strObject, type)
        return myobject
    }


}
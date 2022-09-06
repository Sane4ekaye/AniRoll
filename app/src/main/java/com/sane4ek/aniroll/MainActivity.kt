package com.sane4ek.aniroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sane4ek.aniroll.data.room.UserDatabase
import com.sane4ek.aniroll.data.room.repository.UserRealization
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import com.sane4ek.aniroll.utils.WindowSettings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowSettings().fullScreen(window)

        USER_REPOSITORY = UserRealization(UserDatabase.getInstance(this).getUserDao())
    }
}
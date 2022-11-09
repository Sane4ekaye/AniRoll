package com.sane4ek.aniroll.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll._database.room.UserDatabase
import com.sane4ek.aniroll._database.room.repository.UserRealization
import com.sane4ek.aniroll.utils.PREFS_FIRST_VISIT
import com.sane4ek.aniroll.utils.SharedPrefs
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import com.sane4ek.aniroll.utils.WindowSettings

class MainActivity : AppCompatActivity() {

    private val TAG = "mainact"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowSettings().fullScreen(window)

        USER_REPOSITORY = UserRealization(UserDatabase.getInstance(this).getUserDao())

        val navController = Navigation.findNavController(this, R.id.mainContainerFragment)

//        Log.i(TAG, "onCreate: ${SharedPrefs.getBooleanPrefs(PREFS_FIRST_VISIT, applicationContext)}")
//        Log.i(TAG, "onCreate: ${navController.graph.findStartDestination().displayName}")

        if (SharedPrefs.getBooleanPrefs(PREFS_FIRST_VISIT, applicationContext)) {
            val inflater = navController.navInflater
            val graph = inflater.inflate(R.navigation.main_nav)
            graph.setStartDestination(R.id.splashFragment)
            navController.graph = graph
            navController.setGraph(graph, intent.extras)
        } else {
            val inflater = navController.navInflater
            val graph = inflater.inflate(R.navigation.main_nav)
            graph.setStartDestination(R.id.loginFragment)
            navController.graph = graph
            navController.setGraph(graph, intent.extras)
        }

        if (!SharedPrefs.getBooleanPrefs(PREFS_FIRST_VISIT, applicationContext)) {
            SharedPrefs.saveBooleanPrefs(PREFS_FIRST_VISIT, true, applicationContext)
        }
    }
}
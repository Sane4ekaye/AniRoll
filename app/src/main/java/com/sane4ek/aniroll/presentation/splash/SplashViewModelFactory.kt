package com.sane4ek.aniroll.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sane4ek.aniroll.data.room.UserDatabase
import com.sane4ek.aniroll.data.room.repository.UserRepository

class SplashViewModelFactory(context: Context) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel() as T
    }
}
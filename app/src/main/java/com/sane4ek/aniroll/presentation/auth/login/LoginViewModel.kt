package com.sane4ek.aniroll.presentation.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sane4ek.aniroll.data.room.entities.User
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val TAG = "loginvm"

    private val mutableLoginSuccessLiveData = MutableLiveData<User>()
    val loginSuccessLiveData: LiveData<User>
        get() = mutableLoginSuccessLiveData

    private val mutableLoginFailureLiveData = MutableLiveData<String>()
    val loginFailureLiveData: LiveData<String>
        get() = mutableLoginFailureLiveData

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        val user = USER_REPOSITORY.loginUser(email, password)
        if (user != null) { // успешная авторизация
            mutableLoginSuccessLiveData.value = user
            Log.i(TAG, "id: ${user.id}")
            Log.i(TAG, "email: ${user.email}")
            Log.i(TAG, "password: ${user.password}")
        } else {
            mutableLoginFailureLiveData.value = "пошел нахуй"
            Log.i(TAG, "пошел нахуй $user")
        }
    }
}
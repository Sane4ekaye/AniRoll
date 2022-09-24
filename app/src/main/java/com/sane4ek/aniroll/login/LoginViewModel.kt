package com.sane4ek.aniroll.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sane4ek.aniroll._database.room.entities.User
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val TAG = "loginvm"

    private val mutableLoginSuccessLiveData = MutableLiveData<User>()
    val loginSuccessLiveData: LiveData<User>
        get() = mutableLoginSuccessLiveData

    private val mutableLoginFailureEmailLiveData = MutableLiveData<Boolean>()
    val loginFailureEmailLiveData: LiveData<Boolean>
        get() = mutableLoginFailureEmailLiveData

    private val mutableLoginFailurePasswordLiveData = MutableLiveData<Boolean>()
    val loginFailurePasswordLiveData: LiveData<Boolean>
        get() = mutableLoginFailurePasswordLiveData

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        val userEmailExists = USER_REPOSITORY.getUserEmail(email)
        if (userEmailExists == null) {
            mutableLoginFailureEmailLiveData.postValue(true)
        } else {
            val user = USER_REPOSITORY.loginUser(email, password)
            if (user != null) { // успешная авторизация
                mutableLoginSuccessLiveData.postValue(user)
            } else {
                mutableLoginFailurePasswordLiveData.postValue(true)
            }
        }
    }
}
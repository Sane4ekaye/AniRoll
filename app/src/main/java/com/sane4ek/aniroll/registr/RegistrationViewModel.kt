package com.sane4ek.aniroll.registr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sane4ek.aniroll._database.room.entities.User
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val TAG = "regvm"

    private val mutableRegistrationSuccessLiveData = MutableLiveData<User>()
    val registrationSuccessLiveData: LiveData<User>
        get() = mutableRegistrationSuccessLiveData

    private val mutableRegistrationFailureEmailLiveData = MutableLiveData<String>()
    val registrationFailureEmailLiveData: LiveData<String>
        get() = mutableRegistrationFailureEmailLiveData

    private val mutableRegistrationFailureNicknameLiveData = MutableLiveData<String>()
    val registrationFailureNicknameLiveData: LiveData<String>
        get() = mutableRegistrationFailureNicknameLiveData

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        val userNicknameExists = USER_REPOSITORY.getNickname(user.nickname)
        val userEmailExists = USER_REPOSITORY.getUserEmail(user.email)
        if (userNicknameExists != null && userEmailExists != null) {
            mutableRegistrationFailureNicknameLiveData.postValue("Этот никнейм занят, попробуйте другой")
            mutableRegistrationFailureEmailLiveData.postValue("Пользователь с таким email уже зарегистрирован")
        } else if (userNicknameExists != null) {
            mutableRegistrationFailureNicknameLiveData.postValue("Этот никнейм занят, попробуйте другой")
        } else if (userEmailExists != null) {
            mutableRegistrationFailureEmailLiveData.postValue("Пользователь с таким email уже зарегистрирован")
        } else { // успешная регистрация
            USER_REPOSITORY.addUser(user)
            mutableRegistrationSuccessLiveData.postValue(user)
        }
    }
}
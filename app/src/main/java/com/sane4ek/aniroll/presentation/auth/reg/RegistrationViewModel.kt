package com.sane4ek.aniroll.presentation.auth.reg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sane4ek.aniroll.data.room.entities.User
import com.sane4ek.aniroll.data.room.repository.UserRealization
import com.sane4ek.aniroll.utils.USER_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val mutableRegistrSuccessLiveData = MutableLiveData<User>()
    val registrSuccessLiveData: LiveData<User>
        get() = mutableRegistrSuccessLiveData

    private val mutableRegistrFailureLiveData = MutableLiveData<String>()
    val registrFailureLiveData: LiveData<String>
        get() = mutableRegistrFailureLiveData

    fun registerUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        USER_REPOSITORY.addUser(user)
    }
}
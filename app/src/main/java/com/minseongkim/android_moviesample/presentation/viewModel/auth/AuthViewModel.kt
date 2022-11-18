package com.minseongkim.android_moviesample.presentation.viewModel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.UserState
import com.minseongkim.android_moviesample.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _userInfo = MutableLiveData<Long>()
    val userInfo: LiveData<Long> get() = _userInfo

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState

    /**
     * DB Method
     */
    fun signUp(email: String, password: String, verifyPassword: String) {
        if (email.isNullOrBlank()) {
            _userState.postValue(UserState.Error("Email is Empty"))
        }

        if (password != verifyPassword) {
            _userState.postValue(UserState.Error("Password doesn't not match"))
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = signUpUseCase.signUp(
                    email = email,
                    password = password
                )
                _userInfo.postValue(data)
            } catch (exception: Exception) {
                _userState.postValue(UserState.Error("Sign up Error"))
            }
        }
    }
}
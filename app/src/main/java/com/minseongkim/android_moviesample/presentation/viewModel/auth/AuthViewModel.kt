package com.minseongkim.android_moviesample.presentation.viewModel.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.UserState
import com.minseongkim.android_moviesample.domain.usecase.SignInUseCase
import com.minseongkim.android_moviesample.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
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
            return
        }

        if (password.isNullOrBlank() || password.length < 6) {
            _userState.postValue(UserState.Error("Please check your password. required length more than 6"))
            return
        }

        if (password != verifyPassword) {
            _userState.postValue(UserState.Error("Password doesn't not match"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {

            val validation = signUpUseCase.getExistEmail(email = email)
            if (validation) {
                _userState.postValue(UserState.Error("Email already exist"))
                return@launch
            }

            try {
                val data = signUpUseCase.signUp(
                    email = email,
                    password = password
                )
                _userState.postValue(UserState.Success(data))
                _userInfo.postValue(data)
                return@launch
            } catch (exception: Exception) {
                _userState.postValue(UserState.Error("Sign up Error"))
                return@launch
            }
        }
    }

    fun signIn(email: String, password: String) {

        if (email.isNullOrBlank()) {
            _userState.postValue(UserState.Error("Email is Empty"))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {

            val validation = signUpUseCase.getExistEmail(email = email)
            if (!validation) {
                _userState.postValue(UserState.Error("Can't find your email, check please"))
                return@launch
            }

            try {
                val data = signInUseCase.signIn(email = email, password = password)
                Log.d("TAG", "signIn: $data")
                if (data != 0L) {
                    _userState.postValue(UserState.Success(data))
                    _userInfo.postValue(data)
                    return@launch
                } else {
                    _userState.postValue(UserState.Error("Please check your password."))
                    return@launch
                }
            } catch (exception: Exception) {
                _userState.postValue(UserState.Error("Login failed."))
                return@launch
            }
        }
    }
}
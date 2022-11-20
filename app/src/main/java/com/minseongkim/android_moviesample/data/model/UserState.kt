package com.minseongkim.android_moviesample.data.model

sealed class UserState {
    data class Success(val data: Long) : UserState()
    data class Error(val errorMessage: String) : UserState()
}

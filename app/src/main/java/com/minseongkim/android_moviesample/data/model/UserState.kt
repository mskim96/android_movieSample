package com.minseongkim.android_moviesample.data.model

sealed class UserState {
    object Success : UserState()
    data class Error(val errorMessage: String) : UserState()
}

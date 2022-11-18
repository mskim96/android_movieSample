package com.minseongkim.android_moviesample.data.model

import com.minseongkim.android_moviesample.domain.model.User

sealed class UserState {
    data class Success(val data: User) : UserState()
    data class Error(val errorMessage: String) : UserState()
}

package com.minseongkim.android_moviesample.data.datasource

import com.minseongkim.android_moviesample.data.model.UserEntity

/**
 * User datasource interface.
 */
interface UserDatasource {
    fun signUp(user: UserEntity): Long
    fun signIn(email: String, password: String): Long

    fun getExistEmail(email: String): Boolean
}
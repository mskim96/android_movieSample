package com.minseongkim.android_moviesample.data.datasource.auth

import com.minseongkim.android_moviesample.data.model.auth.UserEntity

/**
 * User datasource interface.
 */
interface UserDatasource {
    fun signUp(user: UserEntity): Long
    fun signIn(email: String, password: String): Long

    fun getExistEmail(email: String): Boolean
    fun getUserById(id: Long): UserEntity
}
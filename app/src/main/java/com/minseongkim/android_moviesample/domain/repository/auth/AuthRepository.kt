package com.minseongkim.android_moviesample.domain.repository.auth

import com.minseongkim.android_moviesample.data.model.auth.UserEntity

interface AuthRepository {
    fun signUp(email: String, password: String): Long
    fun signIn(email: String, password: String): Long

    fun getExistEmail(email: String): Boolean
    fun getUserById(id: Long): UserEntity
}
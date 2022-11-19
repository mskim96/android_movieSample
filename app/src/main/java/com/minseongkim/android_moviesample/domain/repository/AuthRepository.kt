package com.minseongkim.android_moviesample.domain.repository

interface AuthRepository {
    fun signUp(email: String, password: String): Long

    fun getExistEmail(email: String): Boolean
}
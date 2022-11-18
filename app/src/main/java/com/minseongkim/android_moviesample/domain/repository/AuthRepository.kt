package com.minseongkim.android_moviesample.domain.repository

interface AuthRepository {
    fun signUp(email: String, password: String): Long
}
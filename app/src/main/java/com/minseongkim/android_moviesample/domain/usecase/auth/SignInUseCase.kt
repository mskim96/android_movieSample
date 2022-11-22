package com.minseongkim.android_moviesample.domain.usecase.auth

import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun signIn(email: String, password: String): Long {
        return authRepository.signIn(email = email, password = password)
    }

    suspend fun getUserById(id: Long): UserEntity {
        return authRepository.getUserById(id)
    }
}
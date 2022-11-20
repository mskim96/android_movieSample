package com.minseongkim.android_moviesample.domain.usecase

import com.minseongkim.android_moviesample.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun signIn(email: String, password: String): Long {
        return authRepository.signIn(email = email, password = password)
    }
}
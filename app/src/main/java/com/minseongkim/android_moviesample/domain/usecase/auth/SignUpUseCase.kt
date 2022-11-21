package com.minseongkim.android_moviesample.domain.usecase.auth

import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private var authRepository: AuthRepository) {
    suspend fun signUp(email: String, password: String): Long {
        return authRepository.signUp(email = email, password = password)
    }

    suspend fun getExistEmail(email: String): Boolean {
        return authRepository.getExistEmail(email)
    }
}
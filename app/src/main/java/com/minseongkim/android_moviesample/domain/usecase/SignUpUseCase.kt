package com.minseongkim.android_moviesample.domain.usecase

import com.minseongkim.android_moviesample.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private var authRepository: AuthRepository) {
    suspend fun signUp(email: String, password: String): Long {
        return authRepository.signUp(email = email, password = password)
    }

    suspend fun getExistEmail(email: String): Boolean {
        return authRepository.getExistEmail(email)
    }
}
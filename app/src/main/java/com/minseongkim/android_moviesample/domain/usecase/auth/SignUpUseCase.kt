package com.minseongkim.android_moviesample.domain.usecase.auth

import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private var authRepository: AuthRepository) {
    fun signUp(username: String, email: String, password: String): Long {
        return authRepository.signUp(username = username, email = email, password = password)
    }

    fun getExistEmail(email: String): Boolean {
        return authRepository.getExistEmail(email)
    }
}
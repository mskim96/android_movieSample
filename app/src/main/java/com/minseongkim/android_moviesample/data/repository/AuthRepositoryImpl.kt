package com.minseongkim.android_moviesample.data.repository

import com.minseongkim.android_moviesample.data.datasource.UserDatasource
import com.minseongkim.android_moviesample.data.mapper.hashPassword
import com.minseongkim.android_moviesample.data.model.UserEntity
import com.minseongkim.android_moviesample.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val userDatasource: UserDatasource) :
    AuthRepository {
    override fun signUp(
        email: String, password: String
    ): Long {
        return userDatasource.signUp(
            UserEntity(
                email = email,
                password = hashPassword(password),
            )
        )

    }

    override fun getExistEmail(email: String): Boolean {
        return userDatasource.getExistEmail(email)
    }
}
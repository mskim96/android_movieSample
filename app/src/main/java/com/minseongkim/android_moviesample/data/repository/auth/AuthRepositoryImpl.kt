package com.minseongkim.android_moviesample.data.repository.auth

import com.minseongkim.android_moviesample.data.datasource.auth.UserDatasource
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.data.model.auth.UserState
import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val userDatasource: UserDatasource) :
    AuthRepository {
    override fun signUp(username: String, email: String, password: String): Long {
        return userDatasource.signUp(
            UserEntity(
                username = username,
                email = email,
                password = password
            )
        )
    }

    override fun signIn(email: String, password: String): Long {
        return userDatasource.signIn(
            email = email,
            password = password
        )
    }

    override fun getExistEmail(email: String): Boolean {
        return userDatasource.getExistEmail(email)
    }

    override fun getUserById(id: Long): UserEntity {
        return userDatasource.getUserById(id)
    }
}
package com.minseongkim.android_moviesample.data.datasource

import com.minseongkim.android_moviesample.data.db.UserDao
import com.minseongkim.android_moviesample.data.model.UserEntity

class UserDatasourceImpl(private val userDao: UserDao): UserDatasource {
    override suspend fun signUp(): UserEntity {
        TODO("Not yet implemented")
    }
}
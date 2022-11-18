package com.minseongkim.android_moviesample.data.datasource

import com.minseongkim.android_moviesample.data.db.UserDao
import com.minseongkim.android_moviesample.data.model.UserEntity
import javax.inject.Inject

class UserDatasourceImpl @Inject constructor(private val userDao: UserDao): UserDatasource {
    override fun signUp(user: UserEntity): Long {
        return userDao.signUp(user)
    }
}
package com.minseongkim.android_moviesample.data.datasource.auth

import com.minseongkim.android_moviesample.data.db.auth.UserDao
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import javax.inject.Inject

class UserDatasourceImpl @Inject constructor(private val userDao: UserDao) : UserDatasource {
    override fun signUp(user: UserEntity): Long {
        return userDao.signUp(user)
    }

    override fun signIn(email: String, password: String): Long {
        return userDao.signIn(email = email, password = password)
    }

    override fun getExistEmail(email: String): Boolean {
        return userDao.getExistEmail(email)
    }

    override fun getUserById(id: Long): UserEntity {
        return userDao.getUserById(id)
    }
}
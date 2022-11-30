package com.minseongkim.android_moviesample.data.datasource.auth

import com.minseongkim.android_moviesample.data.db.auth.UserDao
import com.minseongkim.android_moviesample.data.db.auth.UserLikeMovie
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override suspend fun getUserLikeMovie(id: Long): Flow<List<Movie>> {
        return flow {
            emit(userDao.getUserLikeMovies(id))
        }
    }

    override fun postLikeMovie(movie: Movie) {
        return userDao.updateUserLikeMovie(movie)
    }
}
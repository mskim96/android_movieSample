package com.minseongkim.android_moviesample.domain.repository.auth

import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUp(username: String, email: String, password: String): Long
    fun signIn(email: String, password: String): Long

    fun getExistEmail(email: String): Boolean
    fun getUserById(id: Long): UserEntity

    fun postLikeMovie(movie: Movie)
}
package com.minseongkim.android_moviesample.domain.usecase.auth

import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLikeMovieUseCase @Inject constructor(val authRepository: AuthRepository) {
    fun postLikeMovie(movie: Movie) {
        authRepository.postLikeMovie(movie)
    }

    suspend fun getUserLikeMovie(id: Long): Flow<List<Movie>> {
        return authRepository.getUserLikeMovie(id)
    }
}
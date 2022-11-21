package com.minseongkim.android_moviesample.domain.usecase.movie

import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetNewMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getNewMovies(): MovieResponse.MovieData? {
        return movieRepository.getNewMovies()
    }
}
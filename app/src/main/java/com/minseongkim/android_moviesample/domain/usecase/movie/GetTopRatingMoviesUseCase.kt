package com.minseongkim.android_moviesample.domain.usecase.movie

import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getTopRatingMovies(): Flow<List<Movie>> {
        return movieRepository.getTopRatingMovies()
    }
}
package com.minseongkim.android_moviesample.domain.usecase.movie

import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getNewMovies(): Flow<List<Movie>> {
       return movieRepository.getNewMovies()
    }
}
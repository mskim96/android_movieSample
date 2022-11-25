package com.minseongkim.android_moviesample.domain.usecase.movie

import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun getGenreDramaMovie(): Flow<List<Movie>> {
        return movieRepository.getGenreDramaMovie()
    }

    suspend fun getGenreHorrorMovie(): Flow<List<Movie>> {
        return movieRepository.getGenreHorrorMovie()
    }

    suspend fun getGenreSFMovie(): Flow<List<Movie>> {
        return movieRepository.getGenreSFMovie()
    }
}
package com.minseongkim.android_moviesample.domain.repository.movie

import com.minseongkim.android_moviesample.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNewMovies(): Flow<List<Movie>>
    suspend fun getTopRatingMovies(): Flow<List<Movie>>
    suspend fun getGenreDramaMovie(): Flow<List<Movie>>
    suspend fun getGenreHorrorMovie(): Flow<List<Movie>>
    suspend fun getGenreSFMovie(): Flow<List<Movie>>

}
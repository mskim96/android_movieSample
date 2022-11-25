package com.minseongkim.android_moviesample.data.datasource.movie

import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    suspend fun getNewMovies(): Flow<List<MovieResponse.MovieData.MovieModel>>
    suspend fun getTopRatingMovies(): Flow<List<MovieResponse.MovieData.MovieModel>>
    suspend fun getGenreDramaMovie(): Flow<List<MovieResponse.MovieData.MovieModel>>
    suspend fun getGenreHorrorMovie(): Flow<List<MovieResponse.MovieData.MovieModel>>
    suspend fun getGenreSFMovie(): Flow<List<MovieResponse.MovieData.MovieModel>>
}
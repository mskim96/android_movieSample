package com.minseongkim.android_moviesample.domain.repository.movie

import com.minseongkim.android_moviesample.data.model.movie.MovieResponse

interface MovieRepository {
    suspend fun getNewMovies() : MovieResponse.MovieData?
}
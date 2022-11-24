package com.minseongkim.android_moviesample.domain.repository.movie

interface MovieRepository {
    suspend fun getNewMovies()
}
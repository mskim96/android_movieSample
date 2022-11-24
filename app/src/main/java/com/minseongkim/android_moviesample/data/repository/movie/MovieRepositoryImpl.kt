package com.minseongkim.android_moviesample.data.repository.movie

import com.minseongkim.android_moviesample.data.datasource.movie.MovieDataSource
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) :
    MovieRepository {
    override suspend fun getNewMovies() {
        // TODO Return Flow
    }
}
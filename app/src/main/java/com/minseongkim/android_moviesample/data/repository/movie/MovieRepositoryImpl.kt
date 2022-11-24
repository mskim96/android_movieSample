package com.minseongkim.android_moviesample.data.repository.movie

import com.minseongkim.android_moviesample.data.datasource.movie.MovieDataSource
import com.minseongkim.android_moviesample.data.mapper.movieMapper
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val dataSource: MovieDataSource) :
    MovieRepository {
    override suspend fun getNewMovies(): Flow<List<Movie>> {
        return dataSource.getNewMovies().map {
            movieMapper(it)
        }
    }
}
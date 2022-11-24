package com.minseongkim.android_moviesample.data.datasource.movie

import com.minseongkim.android_moviesample.data.api.ApiInterface
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    MovieDataSource {
    override suspend fun getNewMovies(): Flow<List<MovieResponse.MovieData.MovieModel>> {
        return flow {
            apiInterface.getNewMovies().body()?.data?.movies?.let { emit(it) }
        }
    }
}
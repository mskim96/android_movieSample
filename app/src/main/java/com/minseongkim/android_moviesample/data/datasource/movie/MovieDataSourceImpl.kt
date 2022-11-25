package com.minseongkim.android_moviesample.data.datasource.movie

import android.util.Log
import com.minseongkim.android_moviesample.data.api.ApiInterface
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    MovieDataSource {
    override suspend fun getNewMovies(): Flow<List<MovieResponse.MovieData.MovieModel>> {
        return flow {
            apiInterface.getNewMovies().body()?.data?.movies?.let { emit(it) }
        }
    }

    override suspend fun getTopRatingMovies(): Flow<List<MovieResponse.MovieData.MovieModel>> {
        return flow {
            apiInterface.getTopRatingMovie().body()?.data?.movies?.let {
                emit(it)
            }
        }
    }
}
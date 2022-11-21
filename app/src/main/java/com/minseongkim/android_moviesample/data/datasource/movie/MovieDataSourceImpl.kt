package com.minseongkim.android_moviesample.data.datasource.movie

import com.minseongkim.android_moviesample.data.api.ApiInterface
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): MovieDataSource {
    override suspend fun getNewMovies(): MovieResponse.MovieData? {
        return apiInterface.getNewMovies().body()?.data
    }
}
package com.minseongkim.android_moviesample.data.datasource.movie

import com.minseongkim.android_moviesample.data.api.ApiInterface
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import okhttp3.Call

interface MovieDataSource{
    suspend fun getNewMovies(): MovieResponse.MovieData?
}
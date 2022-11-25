package com.minseongkim.android_moviesample.data.api

import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/v2/list_movies.json?limit=20?")
    suspend fun getNewMovies(): Response<MovieResponse>

    @GET("/api/v2/list_movies.json?minimum_rating=8")
    suspend fun getTopRatingMovie(): Response<MovieResponse>
}
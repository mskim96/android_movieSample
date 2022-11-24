package com.minseongkim.android_moviesample.data.mapper

import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.domain.model.Movie

fun movieMapper(movies: List<MovieResponse.MovieData.MovieModel>): List<Movie> {
    return movies.let { data ->
        data.map {
            Movie(
                id = it.id,
                title = it.title,
                year = it.year,
                rating = it.rating,
                genres = it.genres,
                description = it.description,
                coverImg = it.coverImg
            )
        }
    }
}
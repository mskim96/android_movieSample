package com.minseongkim.android_moviesample.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("data")
    val data: MovieData
) {
    data class MovieData(
        val movies: List<Movie>
    ) {
        data class Movie(
            @SerializedName("id")
            val id: Int,

            @SerializedName("title")
            val title: String,

            @SerializedName("year")
            val year: Int,

            @SerializedName("rating")
            val rating: Int,

            @SerializedName("genres")
            val genres: List<String>,

            @SerializedName("description_full")
            val description: String,

            @SerializedName("medium_cover_image")
            val coverImg: String
        )
    }
}
package com.minseongkim.android_moviesample.domain.model

data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val year: Int? = null,
    val rating: Float? = null,
    val genres: List<String>? = null,
    val description: String? = null,
    val coverImg: String? = null
)
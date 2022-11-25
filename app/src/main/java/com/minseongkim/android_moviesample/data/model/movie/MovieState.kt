package com.minseongkim.android_moviesample.data.model.movie

sealed class MovieState<T>(
    open val data: T? = null,
    open val message: String? = null
) {
    data class Success<T>(override val data: T) : MovieState<T>(data)
    data class Error<T>(override val message: String, override val data: T?) :
        MovieState<T>(data, message)

    class Loading<T> : MovieState<T>(data = null)
}
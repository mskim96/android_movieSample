package com.minseongkim.android_moviesample.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.minseongkim.android_moviesample.domain.model.Movie

class MovieListConverter {
//    @TypeConverter
//    fun listToJson(value: List<Movie>?): String? {
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    fun jsonToList(value: String): List<Movie>? {
//        return Gson().fromJson(value, Array<Movie>::class.java)?.toList()
//    }

    @TypeConverter
    fun listToGenreJson(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToGenreList(value: String): List<String>? {
        return Gson().fromJson(value, Array<String>::class.java)?.toList()
    }
}
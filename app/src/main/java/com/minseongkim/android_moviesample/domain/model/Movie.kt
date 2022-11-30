package com.minseongkim.android_moviesample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val year: Int? = null,
    val rating: Float? = null,
    val genres: List<String>? = null,
    val description: String? = null,
    val coverImg: String? = null,
    var userId: Long? = null
)
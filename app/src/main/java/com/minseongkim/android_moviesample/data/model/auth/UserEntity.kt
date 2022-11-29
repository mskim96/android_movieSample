package com.minseongkim.android_moviesample.data.model.auth

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.minseongkim.android_moviesample.domain.model.Movie

/**
 * Define User information
 */
@Entity(tableName = "user_information_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = createdAt,

    @Embedded val movie: Movie? = null,
    @ColumnInfo(name = "like_movie")
    val likeMovie: List<Movie>? = listOf()
)
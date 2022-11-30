package com.minseongkim.android_moviesample.data.db.auth

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.minseongkim.android_moviesample.data.datastore.UserManager
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.model.User


@Entity
data class UserLikeMovie(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userId",
    )
    val movies: List<Movie>
)
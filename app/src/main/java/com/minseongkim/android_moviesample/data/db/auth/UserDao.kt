package com.minseongkim.android_moviesample.data.db.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import com.minseongkim.android_moviesample.domain.model.Movie

/**
 * Define User Dao
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUp(user: UserEntity): Long

    @Query("UPDATE user_information_table SET like_movie= :movie")
    fun updateUserLikeMovie(movie: List<Movie>)

    @Query("SELECT * FROM user_information_table WHERE email Like :email AND password Like :password")
    fun signIn(email: String, password: String): Long

    @Query("SELECT EXISTS(SELECT 1 FROM user_information_table WHERE email = :email LIMIT 1)")
    fun getExistEmail(email: String): Boolean

    @Query("SELECT * FROM user_information_table WHERE uid = :id")
    fun getUserById(id: Long): UserEntity

    @Query("SELECT * FROM user_information_table WHERE uid = :id")
    fun getLikeMovieById(id: Long): Map<UserEntity, List<Movie>>
}
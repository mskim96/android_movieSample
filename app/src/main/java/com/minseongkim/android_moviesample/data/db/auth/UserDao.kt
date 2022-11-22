package com.minseongkim.android_moviesample.data.db.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minseongkim.android_moviesample.data.model.auth.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Define User Dao
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUp(user: UserEntity): Long

    @Query("SELECT * FROM user_information_table WHERE email Like :email AND password Like :password")
    fun signIn(email: String, password: String): Long

    @Query("SELECT EXISTS(SELECT 1 FROM user_information_table WHERE email = :email LIMIT 1)")
    fun getExistEmail(email: String): Boolean

    @Query("SELECT * FROM user_information_table WHERE uid = :id")
    fun getUserById(id: Long): UserEntity
}
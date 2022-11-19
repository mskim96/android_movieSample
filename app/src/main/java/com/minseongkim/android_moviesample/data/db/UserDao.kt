package com.minseongkim.android_moviesample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minseongkim.android_moviesample.data.model.UserEntity

/**
 * Define User Dao
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUp(user: UserEntity): Long



    @Query("SELECT * FROM user_information_table WHERE email = :email")
    fun getExistEmail(email: String): Boolean
}
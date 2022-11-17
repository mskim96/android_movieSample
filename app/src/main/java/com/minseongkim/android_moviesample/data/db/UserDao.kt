package com.minseongkim.android_moviesample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.minseongkim.android_moviesample.data.model.UserEntity
import com.minseongkim.android_moviesample.data.model.UserState

/**
 * Define User Dao
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun signUp(user: UserEntity)
}
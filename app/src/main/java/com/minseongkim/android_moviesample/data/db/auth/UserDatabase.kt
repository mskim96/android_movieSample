package com.minseongkim.android_moviesample.data.db.auth

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minseongkim.android_moviesample.data.mapper.MovieListConverter
import com.minseongkim.android_moviesample.data.model.auth.UserEntity

/**
 * Define Room database.
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(MovieListConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
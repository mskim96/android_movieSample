package com.minseongkim.android_moviesample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minseongkim.android_moviesample.data.model.UserEntity

/**
 * Define Room database.
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
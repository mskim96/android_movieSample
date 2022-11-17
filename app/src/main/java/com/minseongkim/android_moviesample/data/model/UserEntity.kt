package com.minseongkim.android_moviesample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Define User information
 */
@Entity(tableName = "user_information_table")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = createdAt,
)
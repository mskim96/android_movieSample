package com.minseongkim.android_moviesample.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserManager(private val dataStore: DataStore<Preferences>) {
    companion object {
        val USER_ID = longPreferencesKey("USER_ID")
    }

    suspend fun storeUser(
        id: Long
    ) {
        dataStore.edit {
            it[USER_ID] = id
        }
    }

    val userIdFlow: Flow<Long?> = dataStore.data.map {
        it[USER_ID] ?: 0
    }
}
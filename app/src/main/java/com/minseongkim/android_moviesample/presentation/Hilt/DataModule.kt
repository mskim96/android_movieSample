package com.minseongkim.android_moviesample.presentation.Hilt

import android.content.Context
import androidx.room.Room
import com.minseongkim.android_moviesample.data.datasource.auth.UserDatasource
import com.minseongkim.android_moviesample.data.datasource.auth.UserDatasourceImpl
import com.minseongkim.android_moviesample.data.db.auth.UserDao
import com.minseongkim.android_moviesample.data.db.auth.AppDatabase
import com.minseongkim.android_moviesample.domain.repository.auth.AuthRepository
import com.minseongkim.android_moviesample.data.repository.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "User.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserDatasource(userDao: UserDao): UserDatasource {
        return UserDatasourceImpl(userDao)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(userDatasource: UserDatasource): AuthRepository {
        return AuthRepositoryImpl(userDatasource)
    }
}
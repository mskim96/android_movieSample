package com.minseongkim.android_moviesample.presentation.Hilt

import com.minseongkim.android_moviesample.data.api.ApiClient
import com.minseongkim.android_moviesample.data.api.ApiInterface
import com.minseongkim.android_moviesample.data.datasource.movie.MovieDataSource
import com.minseongkim.android_moviesample.data.datasource.movie.MovieDataSourceImpl
import com.minseongkim.android_moviesample.data.repository.movie.MovieRepositoryImpl
import com.minseongkim.android_moviesample.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideMovieDataSource(apiInterface: ApiInterface): MovieDataSource {
        return MovieDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieDataSource: MovieDataSource): MovieRepository {
        return MovieRepositoryImpl(movieDataSource)
    }

}
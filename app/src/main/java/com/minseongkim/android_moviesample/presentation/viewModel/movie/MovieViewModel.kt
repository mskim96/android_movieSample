package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.usecase.movie.GetNewMovieUseCase
import com.minseongkim.android_moviesample.domain.usecase.movie.GetTopRatingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNewMovieUseCase: GetNewMovieUseCase,
    private val getTopRatingMoviesUseCase: GetTopRatingMoviesUseCase
) : ViewModel() {

    private val _movieResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val movieResponse = _movieResponse.asStateFlow()

    private val _topRatingResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val topRatingResponse = _topRatingResponse.asStateFlow()

    init {
        getNewMovies()
        getTopRatingMovies()
    }


    private fun getNewMovies() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            getNewMovieUseCase.getNewMovies()
        }.onSuccess { data ->
            _movieResponse.emit(MovieState.Success(data))
        }.onFailure { throwable ->
            _movieResponse.emit(
                MovieState.Error(
                    message = throwable.message.toString(),
                    data = null
                )
            )
        }
    }

    private fun getTopRatingMovies() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            getTopRatingMoviesUseCase.getTopRatingMovies()
        }.onSuccess { data ->
            _topRatingResponse.emit(MovieState.Success(data))
        }.onFailure { throwable ->
            _topRatingResponse.emit(
                MovieState.Error(
                    message = throwable.message.toString(),
                    data = null
                )
            )
        }
    }
}
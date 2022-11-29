package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.usecase.movie.GetGenreMoviesUseCase
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
    private val getTopRatingMoviesUseCase: GetTopRatingMoviesUseCase,
    private val getGenreMoviesUseCase: GetGenreMoviesUseCase,
) : ViewModel() {

    private val _movieResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val movieResponse = _movieResponse.asStateFlow()

    private val _topRatingResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val topRatingResponse = _topRatingResponse.asStateFlow()

    private val _genreDramaResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val genreDramaResponse = _genreDramaResponse.asStateFlow()

    private val _genreHorrorResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val genreHorrorResponse = _genreHorrorResponse.asStateFlow()

    private val _genreSFResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val genreSFResponse = _genreSFResponse.asStateFlow()

    init {
        getNewMovies()
        getTopRatingMovies()
        getGenreDramaMovies()
        getGenreHorrorMovies()
        getGenreSFMovies()
    }


    /**
     * Get Method
     */
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

    private fun getGenreDramaMovies() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            getGenreMoviesUseCase.getGenreDramaMovie()
        }.onSuccess { data ->
            _genreDramaResponse.emit(MovieState.Success(data))
        }.onFailure { throwable ->
            _genreDramaResponse.emit(
                MovieState.Error(
                    message = throwable.message.toString(),
                    data = null
                )
            )
        }
    }

    private fun getGenreHorrorMovies() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            getGenreMoviesUseCase.getGenreHorrorMovie()
        }.onSuccess { data ->
            _genreHorrorResponse.emit(MovieState.Success(data))
        }.onFailure { throwable ->
            _genreHorrorResponse.emit(
                MovieState.Error(
                    message = throwable.message.toString(),
                    data = null
                )
            )
        }
    }

    private fun getGenreSFMovies() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            getGenreMoviesUseCase.getGenreSFMovie()
        }.onSuccess { data ->
            _genreSFResponse.emit(MovieState.Success(data))
        }.onFailure { throwable ->
            _genreSFResponse.emit(
                MovieState.Error(
                    message = throwable.message.toString(),
                    data = null
                )
            )
        }
    }


}
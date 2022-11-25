package com.minseongkim.android_moviesample.presentation.viewModel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.usecase.movie.GetNewMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getNewMovieUseCase: GetNewMovieUseCase) :
    ViewModel() {

    private val _movieResponse: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val movieResponse = _movieResponse.asStateFlow()

    init {
        getNewMovies()
    }


    private fun getNewMovies() = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
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
}
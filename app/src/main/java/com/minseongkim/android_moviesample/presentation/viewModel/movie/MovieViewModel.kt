package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.domain.usecase.movie.GetNewMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getNewMovieUseCase: GetNewMovieUseCase) :
    ViewModel() {
    private val _movieResponse = MutableLiveData<MovieResponse.MovieData>()
    val movieResponse: LiveData<MovieResponse.MovieData> get() = _movieResponse

    fun getNewMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getNewMovieUseCase.getNewMovies()
            Log.d("TAG", "getMovie: $data")
            _movieResponse.postValue(data)
        }
    }
}
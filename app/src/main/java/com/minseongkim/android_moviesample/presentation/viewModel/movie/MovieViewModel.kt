package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.usecase.movie.GetNewMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getNewMovieUseCase: GetNewMovieUseCase) :
    ViewModel() {

//    private val _movieResponse: StateFlow<List<Movie>> = MutableStateFlow(List<Movie>)
//    val movieResponse = _movieResponse.state

    // TODO : Init 시 여러가지 Movie Data 를 가져오게 변경한다.
    // TODO : Loading 시 스켈레톤 UI 가 표시되게 정의한다.

    fun getNewMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getNewMovieUseCase.getNewMovies()
            Log.d("TAG", "getMovie: $data")
//            _movieResponse.postValue(data)
        }
    }
}
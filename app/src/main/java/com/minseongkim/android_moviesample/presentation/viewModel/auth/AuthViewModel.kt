package com.minseongkim.android_moviesample.presentation.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minseongkim.android_moviesample.data.model.auth.UserState
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.domain.usecase.auth.PostLikeMovieUseCase
import com.minseongkim.android_moviesample.domain.usecase.auth.SignInUseCase
import com.minseongkim.android_moviesample.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val postLikeMovieUseCase: PostLikeMovieUseCase
) : ViewModel() {

    private val _userState = MutableSharedFlow<UserState>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val userState = _userState.asSharedFlow()

    private val _likeMovie: MutableStateFlow<MovieState<Flow<List<Movie>>>> =
        MutableStateFlow(MovieState.Loading())
    val likeMovie = _likeMovie.asStateFlow()

    /**
     * DB Method
     */
    fun signUp(username: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {

            val existEmail = signUpUseCase.getExistEmail(email = email)
            if (existEmail) {
                _userState.emit(UserState.Error("Email already exist"))
                return@launch
            }

            if (username.isNullOrBlank()) {
                _userState.emit(UserState.Error("Please write your name!"))
                return@launch
            }

            if (email.isNullOrBlank()) {
                _userState.emit(UserState.Error("Please check your email!"))
                return@launch
            }

            if (password.isNullOrBlank()) {
                _userState.emit(UserState.Error("Please check your password"))
                return@launch
            }

            _userState.emit(
                UserState.Success(
                    signUpUseCase.signUp(
                        username = username,
                        email = email,
                        password = password
                    )
                )
            )
            return@launch
        }

    fun signIn(email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {

            if (email.isNullOrBlank()) {
                _userState.emit(UserState.Error("Email is Empty"))
                return@launch
            }

            val result = signInUseCase.signIn(email = email, password = password)

            if (result == 0L) {
                _userState.emit(UserState.Error("Check your email or password"))
                return@launch
            } else {
                _userState.emit(UserState.Success(result))
                return@launch
            }
        }

    fun getUserLikeMovie(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                postLikeMovieUseCase.getUserLikeMovie(id)
            }.onSuccess { data ->
                _likeMovie.emit(MovieState.Success(data))
            }.onFailure { throwable ->
                _likeMovie.emit(
                    MovieState.Error(
                        message = throwable.message.toString(),
                        data = null
                    )
                )
            }
        }
    }


    fun postLikeMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            postLikeMovieUseCase.postLikeMovie(movie)
        }
    }
}
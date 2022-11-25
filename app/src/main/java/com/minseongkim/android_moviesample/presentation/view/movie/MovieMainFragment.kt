package com.minseongkim.android_moviesample.presentation.view.movie

import android.app.Activity
import android.content.ContextWrapper
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.databinding.FragmentMovieMainBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.presentation.viewModel.movie.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieMainFragment : Fragment() {

    private var _binding: FragmentMovieMainBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            MovieViewModel::class.java
        )
    }
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    private val movieTopRatingAdapter: MovieTopRatingAdapter by lazy {
        MovieTopRatingAdapter()
    }

    private val movieGenreDramaAdapter: MovieGenreDramaAdapter by lazy {
        MovieGenreDramaAdapter()
    }
    private val movieGenreHorrorAdapter: MovieGenreHorrorAdapter by lazy {
        MovieGenreHorrorAdapter()
    }
    private val movieGenreSFAdapter: MovieGenreSFAdapter by lazy {
        MovieGenreSFAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieMainBinding.inflate(inflater, container, false)

        /**
         * Main Video
         */
        val MAIN_VIDEO_PATH =
            "android.resource://" + requireActivity().packageName + "/" + R.raw.sample1024

        val uri: Uri = Uri.parse(MAIN_VIDEO_PATH)
        with(binding) {
            mainVideo.setVideoURI(uri)
            mainVideo.setMediaController(MediaController(requireContext()))
            mainVideo.requestFocus()
            mainVideo.setOnPreparedListener {
                it.start()
            }
        }

        with(binding) {
            mainMovieRecycle.adapter = movieAdapter
            ratingTopMovieRecycle.adapter = movieTopRatingAdapter
            genreDramaMovieRecycle.adapter = movieGenreDramaAdapter
            genreHorrorMovieRecycle.adapter = movieGenreHorrorAdapter
            genreSFMovieRecycle.adapter = movieGenreSFAdapter
        }

        // Main Movie
        lifecycleScope.launch {
            movieViewModel.movieResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieAdapter.setData(it)
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        // Top Rating Movie
        lifecycleScope.launch {
            movieViewModel.topRatingResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieTopRatingAdapter.setData(it)
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        // Genre Drama Movie
        lifecycleScope.launch {
            movieViewModel.genreDramaResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieGenreDramaAdapter.setData(it)
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        // Genre Horror Movie
        lifecycleScope.launch {
            movieViewModel.genreHorrorResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieGenreHorrorAdapter.setData(it)
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        // Genre SF Movie
        lifecycleScope.launch {
            movieViewModel.genreSFResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieGenreSFAdapter.setData(it)
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
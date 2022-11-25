package com.minseongkim.android_moviesample.presentation.view.movie

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
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieAdapter
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieTopRatingAdapter
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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
    private val movieTopRatingAdapter: MovieTopRatingAdapter by lazy { MovieTopRatingAdapter() }

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

        binding.mainMovieRecycle.adapter = movieAdapter
        binding.ratingTopMovieRecycle.adapter = movieTopRatingAdapter
        binding.suggestionMovieRecycle.adapter = movieAdapter

        lifecycleScope.launch {
            movieViewModel.movieResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        movieAdapter.setData(it)
                        Log.d("TAG", "onCreateView: $it")
                    }
                    is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                }
            }
        }

        lifecycleScope.launch {
            movieViewModel.topRatingResponse.collect { data ->
                when (data) {
                    is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                    is MovieState.Success -> data.data.collect {
                        Log.d("TAG", "onCreateView: $it")
                        movieTopRatingAdapter.setData(it)
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
package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.FragmentMovieMainBinding
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieMainFragment : Fragment() {

    private var _binding: FragmentMovieMainBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieMainBinding.inflate(inflater, container, false)

        binding.getButton.setOnClickListener {
            getNewMovies()
        }

        return binding.root
    }

    private fun getNewMovies () {
        movieViewModel.getNewMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
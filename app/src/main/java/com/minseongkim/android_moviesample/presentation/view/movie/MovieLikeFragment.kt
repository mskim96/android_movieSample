package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.data.datastore.UserManager
import com.minseongkim.android_moviesample.data.datastore.dataStore
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.databinding.FragmentMovieLikeBinding
import com.minseongkim.android_moviesample.presentation.viewModel.auth.AuthViewModel
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieAdapter
import com.minseongkim.android_moviesample.presentation.viewModel.movie.MovieLikeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieLikeFragment : Fragment() {

    private var _binding: FragmentMovieLikeBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }
    private val userManager: UserManager by lazy {
        UserManager(requireContext().dataStore)
    }

    private val movieLikeAdapter: MovieLikeAdapter by lazy { MovieLikeAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieLikeBinding.inflate(inflater, container, false)
        binding.movieLikeRecycle.adapter = movieLikeAdapter

        lifecycleScope.launch(Dispatchers.IO) {

            userManager.userIdFlow.collect {
                authViewModel.getUserLikeMovie(it!!)

                authViewModel.likeMovie.collect { data ->
                    when (data) {
                        is MovieState.Loading -> Log.d("TAG", "onCreateView: Loading")
                        is MovieState.Success -> data.data.collect {
                            movieLikeAdapter.setData(it)
                        }
                        is MovieState.Error -> Log.d("TAG", "onCreateView: ${data.message}")
                    }
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
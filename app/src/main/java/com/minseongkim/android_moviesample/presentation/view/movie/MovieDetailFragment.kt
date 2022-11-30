package com.minseongkim.android_moviesample.presentation.view.movie

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.data.datastore.UserManager
import com.minseongkim.android_moviesample.data.datastore.dataStore
import com.minseongkim.android_moviesample.data.model.movie.MovieState
import com.minseongkim.android_moviesample.databinding.FragmentMovieDetailBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.presentation.viewModel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment(val currentMovie: Movie?) : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }
    private val userManager: UserManager by lazy {
        UserManager(requireContext().dataStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        val MAIN_VIDEO_PATH =
            "android.resource://" + requireActivity().packageName + "/" + R.raw.sample2

        val uri: Uri = Uri.parse(MAIN_VIDEO_PATH)
        with(binding) {
            detailVideo.setVideoURI(uri)
            detailVideo.setMediaController(MediaController(requireContext()))
            detailVideo.requestFocus()
            detailVideo.setOnPreparedListener {
                it.start()
            }
        }
        Glide.with(this).load(currentMovie?.coverImg).into(binding.imageView2)

        binding.movie = currentMovie
        CoroutineScope(Dispatchers.IO).launch {
            userManager.userIdFlow.collect {
                val userId = it

                binding.movieLikeButton.setOnClickListener {
                    currentMovie?.userId = userId
                    authViewModel.postLikeMovie(currentMovie!!)
                }

                authViewModel.likeMovie.value.data?.collect {
                    it.map {
                        if (it.id == currentMovie?.id) {
                            binding.movieLikeButton.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                0,
                                R.drawable.movie_thumb_fill_icon
                            );
                        }
                    }
                }

                authViewModel.getUserLikeMovie(it!!)

            }
        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
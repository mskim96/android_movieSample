package com.minseongkim.android_moviesample.presentation.view.movie

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.FragmentMovieDetailBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment(val currentMovie: Movie?) : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

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

        Log.d("TAG", "onCreateView: $currentMovie")

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
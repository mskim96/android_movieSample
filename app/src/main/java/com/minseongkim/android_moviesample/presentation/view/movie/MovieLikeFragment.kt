package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.minseongkim.android_moviesample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieLikeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_like, container, false)
    }

}
package com.minseongkim.android_moviesample.presentation.view.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        supportFragmentManager.beginTransaction()
            .add(R.id.movieMainFrameLayout, MovieMainFragment()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
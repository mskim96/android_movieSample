package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieSearchButton.setOnClickListener {
            binding.movieSearchInput.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.movieMainFrameLayout, MovieMainFragment()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.ActivityMovieBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.presentation.view.auth.AuthSignInFragment
import com.minseongkim.android_moviesample.presentation.view.auth.AuthSignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.movieSearchButton.setOnClickListener {
//            if (binding.movieSearchInput.visibility == View.VISIBLE) {
//                binding.movieSearchInput.visibility = View.GONE
//            } else {
//                binding.movieSearchInput.visibility = View.VISIBLE
//            }
//        }

        supportFragmentManager.beginTransaction()
            .add(R.id.movieMainFrameLayout, MovieMainFragment()).commit()
    }

    fun setFragment(index: Int, data: Movie? = null) {
        when (index) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieMainFrameLayout, MovieMainFragment()).addToBackStack(null)
                    .commit()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieMainFrameLayout, MovieDetailFragment(currentMovie = data))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
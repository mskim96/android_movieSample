package com.minseongkim.android_moviesample.presentation.view.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.ActivityMovieBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private var _binding: ActivityMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = binding.navBottom
        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.main_home -> {
                    if (supportFragmentManager.findFragmentByTag("movie_main") != null) {
                        true
                    } else {
                        setNavFragment(0)
                    }
                }
                R.id.main_likeMovie -> {
                    if (supportFragmentManager.findFragmentByTag("movie_like") != null) {
                        true
                    } else {
                        setNavFragment(1)
                    }
                }

                R.id.main_setting -> {
                    if (supportFragmentManager.findFragmentByTag("movie_setting") != null) {
                        true
                    } else {
                        setNavFragment(2)
                    }
                }
            }
            true
        }


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
                    .replace(R.id.movieMainFrameLayout, MovieMainFragment(), "movie_main")
                    .addToBackStack(null)
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

    fun setNavFragment(index: Int) {
        when (index) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieMainFrameLayout, MovieMainFragment(), "movie_main").commit()
            }

            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieMainFrameLayout, MovieLikeFragment(), "movie_like").commit()
            }

            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.movieMainFrameLayout, MovieSettingFragment(), "movie_setting")
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
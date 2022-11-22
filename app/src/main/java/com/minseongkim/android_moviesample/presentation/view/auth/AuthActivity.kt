package com.minseongkim.android_moviesample.presentation.view.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.authMainFrameLayout, AuthMainFragment()).commit()
    }

    /**
     * function for navigate fragment
     */
    fun setFragment(index: Int) {
        when (index) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.authMainFrameLayout, AuthSignInFragment()).addToBackStack(null)
                    .commit()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.authMainFrameLayout, AuthSignUpFragment()).addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
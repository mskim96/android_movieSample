package com.minseongkim.android_moviesample.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.data.datastore.UserManager
import com.minseongkim.android_moviesample.data.datastore.dataStore
import com.minseongkim.android_moviesample.databinding.ActivityMainBinding
import com.minseongkim.android_moviesample.presentation.view.movie.MovieActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val userManager: UserManager by lazy {
        UserManager(dataStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.authMainFrameLayout, AuthMainFragment()).commit()

        CoroutineScope(Dispatchers.IO).launch {
            userManager.userIdFlow.collect {
                if (it != 0L) {
                    val intent = Intent(this@AuthActivity, MovieActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        }
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
package com.minseongkim.android_moviesample.presentation.view.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.minseongkim.android_moviesample.R
import com.minseongkim.android_moviesample.data.datastore.UserManager
import com.minseongkim.android_moviesample.data.datastore.dataStore
import com.minseongkim.android_moviesample.databinding.FragmentMovieSettingBinding
import com.minseongkim.android_moviesample.presentation.view.auth.AuthActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieSettingFragment : Fragment() {

    private var _binding: FragmentMovieSettingBinding? = null
    private val binding get() = _binding!!
    private val userManager: UserManager by lazy {
        UserManager(requireActivity().dataStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieSettingBinding.inflate(inflater, container, false)

        binding.signOutButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userManager.storeUser(0L)
                val intent = Intent(requireActivity(), AuthActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
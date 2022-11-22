package com.minseongkim.android_moviesample.presentation.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.minseongkim.android_moviesample.databinding.FragmentAuthMainBinding

class AuthMainFragment : Fragment() {

    private var _binding: FragmentAuthMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthMainBinding.inflate(inflater, container, false)

        binding.navSignInButton.setOnClickListener {
            (activity as AuthActivity).setFragment(0)
        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
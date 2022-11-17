package com.minseongkim.android_moviesample.presentation.view.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minseongkim.android_moviesample.databinding.FragmentAuthSignInBinding

class AuthSignInFragment : Fragment() {

    private val TAG: String = "AuthLoginFragment"

    private var _binding: FragmentAuthSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthSignInBinding.inflate(inflater, container, false)

        binding.requestSignInButton.setOnClickListener {
            with(binding) {
                val email = signInEmail.text.toString()
                val password = signInPassword.text.toString()

                Log.d(TAG, "onCreateView: $email, $password")
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
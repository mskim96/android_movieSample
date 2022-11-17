package com.minseongkim.android_moviesample.presentation.view.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minseongkim.android_moviesample.databinding.FragmentAuthSignUpBinding

class AuthSignUpFragment : Fragment() {

    private val TAG: String = "AuthSignUpFragment"

    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthSignUpBinding.inflate(inflater, container, false)

        binding.requestSignUpButton.setOnClickListener {
            with(binding) {
                val email = signUpEmail.text.toString()
                val password = signUpPassword.text.toString()
                val verifyPassword = signUpVerifyPassword.text.toString()

                Log.d(TAG, "onCreateView: $email , $password, $verifyPassword")
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.minseongkim.android_moviesample.presentation.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.minseongkim.android_moviesample.databinding.FragmentAuthSignUpBinding
import com.minseongkim.android_moviesample.presentation.viewModel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthSignUpFragment : Fragment() {

    private val TAG: String = "AuthSignUpFragment"

    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthSignUpBinding.inflate(inflater, container, false)

        binding.requestSignUpButton.setOnClickListener {
            with(binding) {
                val email = signUpEmail.text.toString()
                val password = signUpPassword.text.toString()
                val verifyPassword = signUpVerifyPassword.text.toString()

                signUp(email = email, password = password, verifyPassword = verifyPassword)
            }
        }
        return binding.root
    }

    private fun signUp(email: String, password: String, verifyPassword: String) {
        authViewModel.signUp(email = email, password = password, verifyPassword = verifyPassword)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
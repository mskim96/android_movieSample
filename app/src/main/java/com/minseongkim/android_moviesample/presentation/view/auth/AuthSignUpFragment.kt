package com.minseongkim.android_moviesample.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.minseongkim.android_moviesample.data.model.UserState
import com.minseongkim.android_moviesample.databinding.FragmentAuthSignUpBinding
import com.minseongkim.android_moviesample.presentation.view.movie.MovieActivity
import com.minseongkim.android_moviesample.presentation.viewModel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthSignUpFragment : Fragment() {

    private var _binding: FragmentAuthSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
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

        authViewModel.userState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is UserState.Error -> showToastMessage(it.errorMessage)
                is UserState.Success -> {
                    val intent = Intent(requireActivity(), MovieActivity::class.java)
                    intent.putExtra("uid", it.data)
                    startActivity(intent)
                }
            }
        })

        return binding.root
    }

    private fun signUp(email: String, password: String, verifyPassword: String) {
        authViewModel.signUp(email = email, password = password, verifyPassword = verifyPassword)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.minseongkim.android_moviesample.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.minseongkim.android_moviesample.data.model.auth.UserState
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
                val username = signUpUsername.text.toString()
                val email = signUpEmail.text.toString()
                val password = signUpPassword.text.toString()

                signUp(username = username, email = email, password = password)
            }
        }

        lifecycleScope.launchWhenStarted {
            authViewModel.userState.collect { value ->
                when (value) {
                    is UserState.Error -> showToastMessage(value.errorMessage)
                    is UserState.Success -> {
                        val intent = Intent(requireActivity(), MovieActivity::class.java)
                        intent.putExtra("uid", value.data)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    }
                }
            }
        }

        return binding.root
    }

    private fun signUp(username: String, email: String, password: String) {
        authViewModel.signUp(username = username, email = email, password = password)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
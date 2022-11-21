package com.minseongkim.android_moviesample.presentation.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.minseongkim.android_moviesample.data.model.auth.UserState
import com.minseongkim.android_moviesample.databinding.FragmentAuthSignInBinding
import com.minseongkim.android_moviesample.presentation.view.movie.MovieActivity
import com.minseongkim.android_moviesample.presentation.viewModel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthSignInFragment : Fragment() {

    private var _binding: FragmentAuthSignInBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentAuthSignInBinding.inflate(inflater, container, false)

        binding.requestSignInButton.setOnClickListener {
            with(binding) {
                val email = signInEmail.text.toString()
                val password = signInPassword.text.toString()

                signIn(email = email, password = password)
            }
        }

        binding.requestSignUpButton.setOnClickListener {
            (activity as AuthActivity).setFragment(1)
        }

        authViewModel.userState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is UserState.Error -> showToastMessage(it.errorMessage)
                is UserState.Success -> {
                    val intent = Intent(requireActivity(), MovieActivity::class.java)
                    intent.putExtra("uid", it.data)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                }
            }
        })

        return binding.root
    }

    fun signIn(email: String, password: String) {
        authViewModel.signIn(email = email, password = password)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
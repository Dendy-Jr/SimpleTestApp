package com.dendi.android.feelsoftwaretestapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dendi.android.feelsoftwaretestapp.R
import com.dendi.android.feelsoftwaretestapp.databinding.FragmentWelcomeBinding
import com.dendi.android.feelsoftwaretestapp.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Dendy-Jr on 11.01.2022
 * olehvynnytskyi@gmail.com
 */
class HomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    override fun getViewBinding() = FragmentWelcomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLiveVideo.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_videosFragment)
        }

        binding.btnSignIn.setOnClickListener {
            if (validateUserName() && validatePassword()) {
                viewModel.saveUserName(binding.userNameEditText.text.toString())
                findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
            } else {
                validateUserName()
                validatePassword()
            }
        }
    }

    private fun validateUserName(): Boolean {
        binding.run {
            if (userNameEditText.text.toString().trim().isEmpty()) {
                userNameLayout.error = getString(R.string.required_field)
                userNameEditText.requestFocus()
                return false
            } else {
                userNameLayout.isErrorEnabled = false
            }
            return true
        }
    }

    private fun validatePassword(): Boolean {
        binding.run {
            if (passwordEditText.text.toString().trim().isEmpty()) {
                passwordLayout.error = getString(R.string.required_field)
                passwordEditText.requestFocus()
                return false
            } else {
                passwordLayout.isErrorEnabled = false
            }
        }
        return true
    }
}
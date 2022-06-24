package com.shakatreh.hamditechnicalchallenge.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentLoginBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun setTitle(): String? {
        return context?.getString(R.string.login)
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            showSnackBar(it)
        }
        viewModel.progressStatus.observe(viewLifecycleOwner) {
            if (it) showProgress() else hideProgress()
        }
        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.navigateToHomeFragment.value = false
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }
        viewModel.navigateToRegisterFragment.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.navigateToRegisterFragment.value = false
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }


}
package com.shakatreh.hamditechnicalchallenge.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentHomeBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun setTitle(): String? {
        return context?.getString(R.string.home)
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.adapter = HitsAdapter(listOf(), viewModel)
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
        viewModel.navToDetailsPage.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.navToDetailsPage.value = null
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToImageDetailsFragment(
                        it
                    )
                )
            }
        }
    }
}


package com.shakatreh.hamditechnicalchallenge.screens.imageDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentImageDetailsBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment() : BaseFragment() {
    private lateinit var binding: FragmentImageDetailsBinding
    private val viewModel: ImageDetailsViewModel by activityViewModels<ImageDetailsViewModel>()
    private val args: ImageDetailsFragmentArgs by navArgs()
    override fun setTitle(): String? {
        return context?.getString(R.string.details)
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hit.value = args.hit
        binding.viewpager.adapter =
            activity?.supportFragmentManager?.let { ViewPagerAdapter(it, lifecycle) }
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.section_1)
                1 -> getString(R.string.section_2)
                else -> getString(R.string.section_1)
            }
        }.attach()
    }

}
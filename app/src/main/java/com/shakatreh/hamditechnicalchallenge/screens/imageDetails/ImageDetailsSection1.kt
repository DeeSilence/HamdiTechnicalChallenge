package com.shakatreh.hamditechnicalchallenge.screens.imageDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentImageDetailsSection1Binding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import com.shakatreh.hamditechnicalchallenge.models.Hit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsSection1() : BaseFragment() {
    private lateinit var binding: FragmentImageDetailsSection1Binding
    private val viewModel: ImageDetailsViewModel by activityViewModels<ImageDetailsViewModel>()
    override fun setTitle(): String? {
        return null
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentImageDetailsSection1Binding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
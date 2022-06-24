package com.shakatreh.hamditechnicalchallenge.screens.imageDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentImageDetailsSection2Binding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import com.shakatreh.hamditechnicalchallenge.models.Hit
import com.shakatreh.hamditechnicalchallenge.models.Statistics
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsSection2() : BaseFragment() {
    private lateinit var binding: FragmentImageDetailsSection2Binding
    private val viewModel: ImageDetailsViewModel by activityViewModels<ImageDetailsViewModel>()
    override fun setTitle(): String? {
        return null
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentImageDetailsSection2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.listOfStatistics = emptyList()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hit.observe(viewLifecycleOwner) {
            binding.listOfStatistics = listOf(
                Statistics(
                    icon = R.drawable.ic_user,
                    title = String.format(getString(R.string.uploaded_by), it.user)
                ),
                Statistics(
                    icon = R.drawable.ic_view,
                    title = String.format(getString(R.string.total_views), it.views)
                ),
                Statistics(
                    icon = R.drawable.ic_like,
                    title = String.format(getString(R.string.total_likes), it.likes)
                ),
                Statistics(
                    icon = R.drawable.ic_comment,
                    title = String.format(getString(R.string.total_comments), it.comments)
                ),
                Statistics(
                    icon = R.drawable.ic_favourite,
                    title = String.format(getString(R.string.total_favorites), "0")
                ),
                Statistics(
                    icon = R.drawable.ic_download,
                    title = String.format(getString(R.string.total_downloads), it.downloads)
                ),
            )
        }

    }
}
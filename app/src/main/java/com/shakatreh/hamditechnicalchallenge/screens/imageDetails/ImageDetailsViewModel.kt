package com.shakatreh.hamditechnicalchallenge.screens.imageDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepository
import com.shakatreh.hamditechnicalchallenge.models.Hit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
     val commonRepository: CommonRepository
) : ViewModel() {
    val hit = MutableLiveData<Hit>()
}
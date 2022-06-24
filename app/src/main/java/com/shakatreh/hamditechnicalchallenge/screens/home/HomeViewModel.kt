package com.shakatreh.hamditechnicalchallenge.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepository
import com.shakatreh.hamditechnicalchallenge.models.Hit
import com.shakatreh.hamditechnicalchallenge.repos.PixabayRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    private val pixabayRepo: PixabayRepo,
) : ViewModel(),ClickListener {
    val progressStatus = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()
    val hits = MutableLiveData<List<Hit>>()
    val navToDetailsPage = MutableLiveData<Hit?>()

    init {
        getImages()
    }

    fun getImages() {
        progressStatus.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val images = pixabayRepo.getImages()
                progressStatus.postValue(false)
                if (images.isEmpty())
                    commonRepository.getContext().getString(R.string.no_result_returned)
                hits.postValue(images)
            } catch (e: Exception) {
                progressStatus.postValue(false)
                errorMsg.postValue(e.message)
            }
        }
    }

    override fun onHitClicked(hit: Hit) {
        navToDetailsPage.value = hit
    }
}
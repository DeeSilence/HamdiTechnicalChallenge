package com.shakatreh.hamditechnicalchallenge.repos

import com.shakatreh.hamditechnicalchallenge.models.Hit
import com.shakatreh.hamditechnicalchallenge.network.PixabayApis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PixabayRepo @Inject constructor(val pixabayApis: PixabayApis) {
    suspend fun getImages(): List<Hit> {
        val response = withContext(Dispatchers.IO) {
            pixabayApis.getImages()
        }
        return if (response.isSuccessful)
            response.body()?.hits ?: emptyList()
        else
            emptyList()
    }
}
package com.shakatreh.hamditechnicalchallenge.network

import com.shakatreh.hamditechnicalchallenge.models.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET

interface PixabayApis {
    @GET("/api")
    suspend fun getImages(): Response<ImagesResponse>
}
package com.shakatreh.hamditechnicalchallenge.models

import android.os.Parcelable
import com.shakatreh.hamditechnicalchallenge.globals.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<Hit>,
) : Parcelable

@Parcelize
data class Hit(
    override val id: Int,
    val previewURL: String? = null,
    val views: Int? = null,
    val likes: Int? = null,
    val comments: Int? = null,
    val downloads: Int? = null,
    val imageSize: Int? = null,
    val user: String? = null,
    val largeImageURL: String? = null,
    val type: String? = null,
    val tags: String? = null,
) : Parcelable, ListAdapterItem
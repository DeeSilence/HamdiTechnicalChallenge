package com.shakatreh.hamditechnicalchallenge.screens.home

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.ItemStatisticsBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseAdapter
import com.shakatreh.hamditechnicalchallenge.globals.ListAdapterItem
import com.shakatreh.hamditechnicalchallenge.models.Statistics


@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView, image: String?) {
    image?.let{
        Glide.with(imageView.context)
            .load(it)
            .into(imageView)
    }
}

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView, image: Int) {
    Glide.with(imageView.context)
        .load(image)
        .into(imageView)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = recyclerView.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.updateData(list ?: listOf())
}

@BindingAdapter("statisticsList")
fun statisticsList(layout: LinearLayout, data: List<Statistics>) {
    for (entry in data) {
        ItemStatisticsBinding.inflate(
            LayoutInflater.from(layout.context),
            layout,
            true
        ).apply {
            this.statistics = entry
        }
    }
}
package com.shakatreh.hamditechnicalchallenge.screens.home

import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.ItemImageWithCreaterBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseAdapter
import com.shakatreh.hamditechnicalchallenge.models.Hit

class HitsAdapter(
    private val list: List<Hit>,
    private val clickListener: ClickListener
): BaseAdapter<ItemImageWithCreaterBinding, Hit>(list) {
    override val layoutId: Int = R.layout.item_image_with_creater

    override fun bind(binding: ItemImageWithCreaterBinding, item: Hit) {
        binding.apply {
            hit = item
            listener = clickListener
            executePendingBindings()
        }
    }
}
interface ClickListener {
    fun onHitClicked(hit: Hit)
}
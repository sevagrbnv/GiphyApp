package com.example.giphyapp.presentation.mainFragment.recView

import androidx.recyclerview.widget.DiffUtil
import com.example.giphyapp.domain.model.Gif

class GifDiffCallback: DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean = oldItem == newItem
}
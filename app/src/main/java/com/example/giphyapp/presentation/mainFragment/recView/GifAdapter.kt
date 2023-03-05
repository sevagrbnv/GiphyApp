package com.example.giphyapp.presentation.mainFragment.recView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.giphyapp.R
import com.example.giphyapp.databinding.ListItemBinding
import com.example.giphyapp.domain.model.Gif

class GifAdapter : ListAdapter<Gif, GifViewHolder>(
    GifDiffCallback()
    ) {
        var onItemClickListener: ((Int) -> Unit)? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
            )
            return GifViewHolder(binding)
        }

        override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
            val item = getItem(position)
            val binding = holder.binding

            when (binding) {
                is ListItemBinding -> {
                    Glide.with(holder.itemView.context).asGif().load(item.widthUrl).into(binding.imageMain)
                    binding.titleMain.text = item.title
                    binding.root.setOnClickListener {
                        onItemClickListener?.invoke(position)
                    }
                }
            }
        }
}
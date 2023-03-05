package com.example.giphyapp.presentation.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.giphyapp.databinding.FragmentDetailBinding
import com.example.giphyapp.domain.model.Gif
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel by viewModels<DetailViewModel>()

    private var itemPosition = UNDEFINED_POSITION
    private var item: Gif? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item = viewModel.getItem(itemPosition)
        Glide.with(view.context).asGif().load(item?.heightUrl).into(binding.imageDetail)
        binding.datetimeDetail.text = item?.datetime
        binding.ratingDetail.text = item?.rating
        binding.titleDetail.text = item?.title
        binding.usernameDetail.text = item?.username
    }

    private fun parseParam() {
        if (DetailFragmentArgs.fromBundle(requireArguments()).position != (-1)) {
            itemPosition = DetailFragmentArgs.fromBundle(requireArguments()).position
        } else throw RuntimeException("Undefined position")
    }

    companion object {
        private const val UNDEFINED_POSITION = -1
    }

}
package com.example.giphyapp.domain

import com.example.giphyapp.data.api.NetworkResult
import com.example.giphyapp.domain.model.Gif

interface GifRepository {
    suspend fun getListOfGifs(query: String): NetworkResult<List<Gif>>

    fun getItemFromList(position: Int): Gif?
}
package com.example.giphyapp.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val giphyService: GiphyService
) {
    suspend fun getAllGifs(query: String) = giphyService.getAllGifs(query = query)
}
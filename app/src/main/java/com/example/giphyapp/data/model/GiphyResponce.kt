package com.example.giphyapp.data.model

data class GiphyResponce(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)
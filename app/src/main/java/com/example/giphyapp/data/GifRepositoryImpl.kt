package com.example.giphyapp.data

import com.example.giphyapp.data.api.BaseApiResponse
import com.example.giphyapp.data.api.NetworkResult
import com.example.giphyapp.data.api.RemoteDataSource
import com.example.giphyapp.data.model.Mapper
import com.example.giphyapp.domain.model.Gif
import com.example.giphyapp.domain.GifRepository
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
): BaseApiResponse(),GifRepository {

    private lateinit var listOfGifs: NetworkResult<List<Gif>>

    override suspend fun getListOfGifs(query: String): NetworkResult<List<Gif>> {
        listOfGifs = safeApiResponse({ remoteDataSource.getAllGifs(query) }, mapper)
        return listOfGifs
    }

    override fun getItemFromList(position: Int): Gif? {
        if (position < (listOfGifs.data?.size ?: 0))
            return listOfGifs.data?.get(position)
        else throw RuntimeException("Error! Access to empty item")
    }
}
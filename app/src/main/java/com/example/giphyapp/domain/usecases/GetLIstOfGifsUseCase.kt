package com.example.giphyapp.domain.usecases

import com.example.giphyapp.domain.GifRepository
import javax.inject.Inject

class GetLIstOfGifsUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {
    suspend fun execute(query: String) = gifRepository.getListOfGifs(query)
}
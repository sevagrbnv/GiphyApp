package com.example.giphyapp.domain.usecases

import com.example.giphyapp.domain.GifRepository
import javax.inject.Inject

class GetItemFromListUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {
    fun execute(position: Int) = gifRepository.getItemFromList(position)
}
package com.example.giphyapp.data.model

import com.example.giphyapp.domain.model.Gif
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun mapDataToGif(data: Data) = Gif(
        id = data.id,
        rating = data.rating,
        title = data.title,
        widthUrl = data.images.fixed_width.url,
        heightUrl = data.images.fixed_height.url,
        username = data.username,
        datetime = data.import_datetime
    )

    fun mapDataListToGifMap(list: List<Data>) = list.map {
        mapDataToGif(it)
    }
}
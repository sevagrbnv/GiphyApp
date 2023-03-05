package com.example.giphyapp.data.api

import com.example.giphyapp.data.model.GiphyResponce
import com.example.giphyapp.data.model.Mapper
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiResponse(
        api: suspend () -> Response<GiphyResponce>,
        mapper: Mapper
    ): NetworkResult<T> {
        try {
            val response = api()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val convertData = mapper.mapDataListToGifMap(body.data)
                    return NetworkResult.Success(convertData as T)
                } ?: return errorMessage("Body is empty")
            } else return errorMessage("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return errorMessage(e.message.toString())
        }
    }

    private fun <T> errorMessage(message: String): NetworkResult.Error<T> =
        NetworkResult.Error(data = null, message = "Api call failed: $message")
}
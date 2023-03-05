package com.example.giphyapp.data.api

import com.example.giphyapp.data.model.GiphyResponce
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("v1/gifs/search")
    suspend fun getAllGifs(
        @Query("api_key") api_key: String = API_KEY,
        @Query("q") query: String,
        @Query("limit") limit: Int = LIMIT
    ): Response<GiphyResponce>

    companion object {
        private const val API_KEY = "cyei0TCgs6cWAuaFFHK8AU2sCgHpbAZE"
        private const val LIMIT = 25
    }
}
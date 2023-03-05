package com.example.giphyapp.di

import com.example.giphyapp.data.GifRepositoryImpl
import com.example.giphyapp.data.api.RemoteDataSource
import com.example.giphyapp.data.api.GiphyService
import com.example.giphyapp.data.model.Mapper
import com.example.giphyapp.domain.GifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val  BASE_URL = "https://api.giphy.com/"
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesGiphyService(retrofit: Retrofit): GiphyService =
        retrofit.create(GiphyService::class.java)

    @Provides
    @Singleton
    fun provideRepositoty(
        remoteDataSource: RemoteDataSource,
        mapper: Mapper
    ): GifRepository {
        return GifRepositoryImpl(
            remoteDataSource = remoteDataSource,
            mapper = mapper
        )
    }
}
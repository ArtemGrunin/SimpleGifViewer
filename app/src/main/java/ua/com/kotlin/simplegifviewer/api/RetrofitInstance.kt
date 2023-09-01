package ua.com.kotlin.simplegifviewer.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.com.kotlin.simplegifviewer.service.GiphyApiService

object RetrofitInstance {
    private const val BASE_URL = "https://api.giphy.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}

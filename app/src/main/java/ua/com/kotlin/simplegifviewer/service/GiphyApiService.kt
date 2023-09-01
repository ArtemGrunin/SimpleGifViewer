package ua.com.kotlin.simplegifviewer.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.kotlin.simplegifviewer.model.GifResponse

interface GiphyApiService {
    @GET("v1/gifs/search")
    fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): Call<GifResponse>
}

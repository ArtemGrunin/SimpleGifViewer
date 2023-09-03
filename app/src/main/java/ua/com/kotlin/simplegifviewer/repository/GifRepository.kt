package ua.com.kotlin.simplegifviewer.repository

import ua.com.kotlin.simplegifviewer.api.RetrofitInstance
import ua.com.kotlin.simplegifviewer.model.GifResponse

class GifRepository {

    suspend fun searchGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ): GifResponse {
        return RetrofitInstance.api.searchGifs(apiKey, query, limit, offset, rating, lang)
    }
}

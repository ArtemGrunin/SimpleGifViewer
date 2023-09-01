package ua.com.kotlin.simplegifviewer

import ua.com.kotlin.simplegifviewer.api.RetrofitInstance

fun main() {
    val service = RetrofitInstance.api

    val response = service.searchGifs(
        "DROe0xlRpyI8H3AprW8uCWwUaF8y7bF9",
        "funny",
        10,
        0,
        "g",
        "en")
        .execute()

    if (response.isSuccessful) {
        val gifs = response.body()?.data
        println("Number of GIFs received: ${gifs?.size}")
    } else {
        println("Error in response: ${response.errorBody()}")
    }
}

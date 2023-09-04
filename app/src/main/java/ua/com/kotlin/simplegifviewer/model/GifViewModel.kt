package ua.com.kotlin.simplegifviewer.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.com.kotlin.simplegifviewer.R
import ua.com.kotlin.simplegifviewer.repository.GifRepository

class GifViewModel(
    private val repository: GifRepository,
    private val applicationContext: Context
) : ViewModel() {

    val gifs: MutableLiveData<List<GifData>> = MutableLiveData()

    fun searchGifs(
        query: String,
        offset: Int = 0,
        limit: Int,
        rating: String = "g",
        lang: String = "en"
    ) {
        viewModelScope.launch {
            val apiKey = applicationContext.getString(R.string.giphy_api_key)
            val response = repository.searchGifs(apiKey, query, limit, offset, rating, lang)
            gifs.postValue(response.data)
        }
    }
}

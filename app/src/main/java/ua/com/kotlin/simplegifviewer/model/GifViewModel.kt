package ua.com.kotlin.simplegifviewer.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.com.kotlin.simplegifviewer.R
import ua.com.kotlin.simplegifviewer.adapter.GifAdapter
import ua.com.kotlin.simplegifviewer.model.GifResponse
import ua.com.kotlin.simplegifviewer.repository.GifRepository

class GifViewModel(
    private val repository: GifRepository,
    private val context: Context
) : ViewModel() {

    val gifs: MutableLiveData<GifResponse> = MutableLiveData()

    fun searchGifs(
        query: String,
        limit: Int = 25,
        offset: Int = 0,
        rating: String = "g",
        lang: String = "en"
    ) {
        viewModelScope.launch {
            val apiKey = context.getString(R.string.giphy_api_key)
            val response = repository.searchGifs(apiKey, query, limit, offset, rating, lang)
            gifs.postValue(response)
        }
    }

    fun updateAdapterData(adapter: GifAdapter, newData: List<GifData>) {
        adapter.updateData(newData)
    }
}


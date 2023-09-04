package ua.com.kotlin.simplegifviewer.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.com.kotlin.simplegifviewer.repository.GifRepository

class GifViewModelFactory(
    private val repository: GifRepository,
    private val applicationContext: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GifViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GifViewModel(repository, applicationContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

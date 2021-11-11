package com.example.flickerfindr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickerfindr.api.FlickrApi
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel () {
    private var _success = MutableLiveData<String>("init value")
    val success : LiveData<String> = _success

    init {
        getSearchResults("birds")
    }

    fun getSearchResults( searchTerm : String) {
        try {
            viewModelScope.launch {
                val photos = FlickrApi.retrofitService.echoTest(searchTerm) //FlickrApi.retrofitService.getSearchPhotos(searchTerm)
                _success.value = "Got photos ${photos.photoData.photos[0]}"
            }

        }
        catch (e: Exception) {
            _success.value = "error ${e.message}"
        }


    }
}
package com.example.flickerfindr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickerfindr.api.FlickrApi
import com.example.flickerfindr.api.FlickrPhoto
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel () {
    private val _selectedImage = MutableLiveData<String>()
    val selectedImage: LiveData<String> = _selectedImage

    private val _displayedPhotos = MutableLiveData<List<FlickrPhoto>>()
    val displayPhotos: LiveData<List<FlickrPhoto>> = _displayedPhotos

    private val _searchNewest = MutableLiveData<Boolean>(true)
    val searchNewest: LiveData<Boolean> = _searchNewest

    private val _searchLocal = MutableLiveData<Boolean>(true)
    val searchLocal: LiveData<Boolean> = _searchLocal


    fun setSelectedImage(selected: FlickrPhoto) {
        _selectedImage.value = constructImageUrl(selected)
    }

    private fun constructImageUrl(photo: FlickrPhoto, size: String = "w"): String {
        return "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_${size}.jpg"
    }

    fun getSearchResults(searchTerm: String) {
        if (searchTerm.isEmpty()) return
        try {
            viewModelScope.launch {

                val localStr = if (_searchLocal.value == true) "6" else "1"
                val newestStr =
                    if (_searchNewest.value == true) "date-posted-desc" else "date-posted-asc"
                val photos = FlickrApi.retrofitService.getSearchPhotos(
                    searchTerm,
                    localStr,
                    newestStr
                )
                _displayedPhotos.value = photos.photoData.photos.subList(0, 25)
            }

        } catch (e: Exception) {
            TODO("Add exception handling")
        }
    }

    fun setSearchLocal(checked: Boolean) {
        _searchLocal.value = checked
    }

    fun setSearchNewest(checked: Boolean) {
        _searchNewest.value = checked
    }
}
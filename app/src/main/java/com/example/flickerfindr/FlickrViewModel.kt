package com.example.flickerfindr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickerfindr.api.FlickrApi
import com.example.flickerfindr.api.FlickrPhoto
import kotlinx.coroutines.launch


enum class FlickrApiStatus { LOADING, ERROR, DONE }

class FlickrViewModel : ViewModel() {
    private val _selectedImage = MutableLiveData<String>()
    val selectedImage: LiveData<String> = _selectedImage

    private val _displayedPhotos = MutableLiveData<List<FlickrPhoto>>()
    val displayPhotos: LiveData<List<FlickrPhoto>> = _displayedPhotos

    private val _searchNewest = MutableLiveData<Boolean>(true)
    val searchNewest: LiveData<Boolean> = _searchNewest

    private val _searchLocal = MutableLiveData<Boolean>(true)
    val searchLocal: LiveData<Boolean> = _searchLocal

    private val _status = MutableLiveData<FlickrApiStatus>()
    val status: LiveData<FlickrApiStatus> = _status

    private val _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> = _page

    fun setSelectedImage(selected: FlickrPhoto) {
        _selectedImage.value = constructImageUrl(selected)
    }

    private fun constructImageUrl(photo: FlickrPhoto, size: String = "w"): String {
        return "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_${size}.jpg"
    }

    fun getSearchResults(searchTerm: String) {
        if (searchTerm.isEmpty()) return
        _status.value = FlickrApiStatus.LOADING
            viewModelScope.launch {
                try {
                    val localStr = if (_searchLocal.value == true) "6" else "1"
                    val newestStr =
                        if (_searchNewest.value == true) "date-posted-desc" else "date-posted-asc"
                    val photos = FlickrApi.retrofitService.getSearchPhotos(
                        searchTerm = searchTerm,
                        localStr,
                        newestStr,
                        _page.value.toString()
                    )
                    _displayedPhotos.value = photos.photoData.photos
                    _status.value = FlickrApiStatus.DONE
                } catch (e: Exception) {
                    _displayedPhotos.value = listOf()
                    _status.value = FlickrApiStatus.ERROR
                }
            }


    }

    fun setSearchLocal(checked: Boolean) {
        _searchLocal.value = checked
    }

    fun setSearchNewest(checked: Boolean) {
        _searchNewest.value = checked
    }

    fun decreasePage() {
        _page.value = _page.value?.minus(1)
    }

    fun increasePage() {
        _page.value = _page.value?.plus(1)
    }
}
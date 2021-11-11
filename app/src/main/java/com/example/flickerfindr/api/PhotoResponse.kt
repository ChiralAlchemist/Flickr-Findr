package com.example.flickerfindr.api

import com.squareup.moshi.Json

data class PhotoResponse(
    @Json(name = "photos") val photoData: Photodata
)

data class Photodata(
    @Json(name = "photo") val photos : List<FlickrPhoto>
)
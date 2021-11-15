package com.example.flickerfindr.api

data class FlickrPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String
)


package com.example.flickerfindr.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private const val BASE_URL = "https://www.flickr.com/"
private const val BASE_URL = "https://www.flickr.com/"
private const val API_KEY = "1508443e49213ff84d566777dc211f2a"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlickerApiService {

    @GET("services/rest/?format=json&nojsoncallback=1&method=flickr.photos.search&api_key=${API_KEY}&text&accuracy&sort")
    suspend fun getSearchPhotos(
        @Query("text") searchTerm: String,
        @Query("accuracy") local: String,
        @Query("sort") newest: String
    ): PhotoResponse
}

object FlickrApi {
    val retrofitService : FlickerApiService by lazy { retrofit.create(FlickerApiService::class.java) }
}
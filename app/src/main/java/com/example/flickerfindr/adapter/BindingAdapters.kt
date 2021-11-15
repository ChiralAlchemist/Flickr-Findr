package com.example.flickerfindr.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.flickerfindr.R
import com.example.flickerfindr.api.FlickrPhoto


@BindingAdapter("photoData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FlickrPhoto>?) {
    val adapter = recyclerView.adapter as FlickrListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.image_broken_variant)
            )
            .into(imgView)
    }
}
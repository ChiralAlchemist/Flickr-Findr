package com.example.flickerfindr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerfindr.api.FlickrPhoto
import com.example.flickerfindr.databinding.GridViewItemBinding

class FlickrListAdapter(private val onItemClicked: (FlickrPhoto) -> Unit) :
    ListAdapter<FlickrPhoto, FlickrListAdapter.FlickrPhotoViewHolder>(DiffCallback) {

    class FlickrPhotoViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: FlickrPhoto) {
            binding.flickrPhoto = photo

            binding.urlStr =
                "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_w.jpg"
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlickrListAdapter.FlickrPhotoViewHolder {
        return FlickrPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: FlickrListAdapter.FlickrPhotoViewHolder, position: Int) {
        val flickrPhoto = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(flickrPhoto)
        }
        holder.bind(flickrPhoto)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FlickrPhoto>() {
        override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            // making assumption that if the id is the same that the image hasn't been altered
            return oldItem.id == newItem.id
        }
    }
}


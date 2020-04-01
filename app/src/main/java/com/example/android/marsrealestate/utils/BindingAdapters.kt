

package com.example.android.marsrealestate.utils

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.data.models.MarsProperty
import com.example.android.marsrealestate.ui.overview.DataAvailabilityStatus
import com.example.android.marsrealestate.ui.overview.PhotoGridAdapter

@BindingAdapter("imageurl")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
                .load(uri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: DataAvailabilityStatus?) {
    when (status) {
        DataAvailabilityStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DataAvailabilityStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DataAvailabilityStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


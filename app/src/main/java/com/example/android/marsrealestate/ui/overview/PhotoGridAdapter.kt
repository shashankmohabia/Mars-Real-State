package com.example.android.marsrealestate.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.data.models.Property
import com.example.android.marsrealestate.databinding.GridViewItemBinding

class PhotoGridAdapter(private val clickListener: OnClickListener) : ListAdapter<Property, PhotoGridViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        return PhotoGridViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class PhotoGridViewHolder private constructor(var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): PhotoGridViewHolder {
            return PhotoGridViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    fun bind(property: Property, clickListener: OnClickListener) {
        binding.property = property
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class OnClickListener(val clickListener: (property: Property) -> Unit) {
    fun onClick(property: Property) = clickListener(property)
}



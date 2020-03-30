package com.example.android.marsrealestate.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

class PhotoGridAdapter(private val clickListener: OnClickListener) : ListAdapter<MarsProperty, PhotoGridViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        return PhotoGridViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
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

    fun bind(marsProperty: MarsProperty, clickListener: OnClickListener) {
        binding.property = marsProperty
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }
}

class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
    fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
}



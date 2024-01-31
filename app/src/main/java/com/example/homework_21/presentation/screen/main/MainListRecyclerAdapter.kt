package com.example.homework_21.presentation.screen.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_21.databinding.ListItemBinding
import com.example.homework_21.presentation.model.item.ItemModel

class MainListRecyclerAdapter :
    ListAdapter<ItemModel, MainListRecyclerAdapter.ItemListViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.bind()
    }

    inner class ItemListViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: ItemModel
        fun bind() {
            item = currentList[adapterPosition]
            binding.apply {
                Glide.with(ivImage.context)
                    .load(item.cover)
                    .into(ivImage)
                tvTitle.text = item.title
                tvPrice.text = item.price
                ivHeart.isVisible = item.favorite
            }
        }
    }
    private class UserListDiffCallback : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
}
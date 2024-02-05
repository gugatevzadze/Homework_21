package com.example.homework_21.presentation.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_21.R
import com.example.homework_21.databinding.CategoryItemBinding

class MainCategoryRecyclerAdapter
    (private val onCategoryClick: (String) -> Unit) :
    ListAdapter<String, MainCategoryRecyclerAdapter.CategoryViewHolder>(CategoryDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private var selectedPosition = 0
    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String, position: Int) {
            binding.apply {
                categoryName.text = category
                root.setOnClickListener {
                    onCategoryClick(category)
                    notifyItemChanged(selectedPosition)
                    selectedPosition = position
                    notifyItemChanged(selectedPosition)
                }
                if (position == selectedPosition) {
                    categoryName.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemView.setBackgroundResource(R.drawable.category_background_selected)
                } else {
                    categoryName.setTextColor(ContextCompat.getColor(itemView.context, R.color.category_2))
                    itemView.setBackgroundResource(R.drawable.category_background)
                }
            }
        }
    }

    private class CategoryDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
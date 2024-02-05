package com.example.homework_21.presentation.state

import com.example.homework_21.presentation.model.item.ItemModel

data class MainState(
    val items: List<ItemModel>? = null,
    val categories: List<String>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
package com.example.homework_21.presentation.event

sealed class MainEvents {
    data object GetItems : MainEvents()
    data object UpdateErrorMessages : MainEvents()

    data object RefreshData : MainEvents()

    data object GetCategories : MainEvents()
    data class GetItemsByCategory(val category: String) : MainEvents()
}
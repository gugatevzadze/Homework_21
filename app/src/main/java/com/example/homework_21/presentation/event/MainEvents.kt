package com.example.homework_21.presentation.event

sealed class MainEvents {
    data object GetItems : MainEvents()
    data object UpdateErrorMessages : MainEvents()
}
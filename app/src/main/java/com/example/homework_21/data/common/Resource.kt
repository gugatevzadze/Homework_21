package com.example.homework_21.data.common

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorMessage: String) : Resource<T>()
    data class Loading<Nothing>(val loading: Boolean) : Resource<Nothing>()
}
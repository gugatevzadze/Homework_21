package com.example.homework_21.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkConnectionChecker {

    val networkStateFlow: Flow<Boolean>

    fun isConnected(): Boolean
}
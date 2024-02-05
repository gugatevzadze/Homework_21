package com.example.homework_21.domain.util

import kotlinx.coroutines.flow.Flow

interface NetworkConnectionChecker {

    val networkStateFlow: Flow<Boolean>

    fun isConnected(): Boolean
}
package com.example.homework_21.domain.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NetworkConnectionCheckerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkConnectionChecker {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkCallback = NetworkCallback()

    private val _networkStateFlow = MutableStateFlow(false)
    override val networkStateFlow = _networkStateFlow.asStateFlow()

    private inner class NetworkCallback : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            updateNetworkState(true)
        }

        override fun onLost(network: Network) {
            updateNetworkState(false)
        }
    }

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun updateNetworkState(isConnected: Boolean) {
        _networkStateFlow.value = isConnected
    }
}
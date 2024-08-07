package com.example.papper.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class CheckNetworkStatus @Inject constructor(
    private val context: Context,
) {
    suspend fun isNetworkConnected(
        onSuccess: suspend() -> Unit,
        onFail: suspend() -> Unit
    ) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork
        val capabilities = cm.getNetworkCapabilities(network)
        if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
            onSuccess()
        } else {
            onFail()
        }

    }
}
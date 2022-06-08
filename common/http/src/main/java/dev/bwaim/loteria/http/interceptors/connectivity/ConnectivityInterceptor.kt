/*
 * Copyright 2022 Dev Bwaim team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.bwaim.loteria.http.interceptors.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

internal class ConnectivityInterceptor @Inject constructor(
    @ApplicationContext context: Context
) : Interceptor, ConnectivityManager.NetworkCallback() {
    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!
    private var connected = false

    init {
        if (Build.VERSION.SDK_INT >= 24) {
            connectivityManager.registerDefaultNetworkCallback(this)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (Build.VERSION.SDK_INT < 24) {
            @SuppressWarnings("Deprecated")
            connected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

        if (!connected) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    override fun onCapabilitiesChanged(
        network: Network,
        capabilities: NetworkCapabilities
    ) {
        connected = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

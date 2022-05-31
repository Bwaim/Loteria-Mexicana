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

package dev.bwaim.loteria.http.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.http.BuildConfig
import dev.bwaim.loteria.http.interceptors.connectivity.ConnectivityInterceptor
import dev.bwaim.loteria.http.interceptors.emptybody.EmptyBodyInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

private const val TIMEOUT = 30_000L // in ms

@Module
@InstallIn(SingletonComponent::class)
internal object HttpModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        connectivityInterceptor: ConnectivityInterceptor,
        emptyBodyInterceptor: EmptyBodyInterceptor
    ) = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(connectivityInterceptor)
        .addInterceptor(emptyBodyInterceptor)
        .apply {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
            }
        }
        .cache(Cache(File(context.cacheDir, "http_cache"), 50L * 1024L * 1024L))
        .build()
}

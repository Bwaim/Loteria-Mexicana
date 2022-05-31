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

package dev.bwaim.loteria.imageloading

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import androidx.core.content.getSystemService
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.annotation.ExperimentalCoilApi
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.util.DebugLogger
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.bwaim.loteria.coroutines.IODispatcher
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

private const val CACHE_DIRECTORY_NAME = "image_cache"
private const val MEMORY_CACHE_PERCENT = 0.25

@OptIn(ExperimentalCoilApi::class)
@Module
@InstallIn(SingletonComponent::class)
internal object ImageLoaderModule {
    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        @IODispatcher ioDispatcher: CoroutineDispatcher,
        okHttpClientLazy: Lazy<OkHttpClient>
    ) = ImageLoaderFactory {
        with(context) {
            ImageLoader.Builder(this)
                .dispatcher(ioDispatcher)
                .memoryCache {
                    MemoryCache.Builder(this)
                        .maxSizePercent(MEMORY_CACHE_PERCENT)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(filesDir.resolve(CACHE_DIRECTORY_NAME))
                        .build()
                }
                .okHttpClient {
                    val dispatcher = Dispatcher().apply { maxRequestsPerHost = maxRequests }
                    okHttpClientLazy.get()
                        .newBuilder()
                        .cache(null)
                        .dispatcher(dispatcher)
                        .build()
                }
                .respectCacheHeaders(false)
                .apply {
                    val activityManager = getSystemService<ActivityManager>()!!
                    allowRgb565(activityManager.isLowRamDevice)

                    if (BuildConfig.DEBUG) {
                        logger(DebugLogger(Log.VERBOSE))
                    }
                }
                .build()
        }
    }
}

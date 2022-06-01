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

package dev.bwaim.loteria.http.interceptors.emptybody

import androidx.annotation.VisibleForTesting
import java.net.HttpURLConnection.HTTP_NO_CONTENT
import java.net.HttpURLConnection.HTTP_RESET
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

internal class EmptyBodyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful ||
            (response.code != HTTP_NO_CONTENT && response.code != HTTP_RESET)
        ) {
            return response
        }

        return response
            .newBuilder()
            .code(200)
            .apply {
                val body = response.body
                if (body == null || body.contentLength() == 0L) {
                    body(EMPTY_BODY)
                }
            }
            .build()
    }

    companion object {
        @VisibleForTesting
        val EMPTY_BODY = "".toResponseBody("text/plain".toMediaType())
    }
}

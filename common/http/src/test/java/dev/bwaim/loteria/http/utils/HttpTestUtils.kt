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

package dev.bwaim.loteria.http.utils

import io.mockk.mockk
import java.net.HttpURLConnection
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

internal object HttpTestUtils {
    fun createFakeResponse(
        code: Int = HttpURLConnection.HTTP_OK,
        request: Request = mockk(relaxed = true),
        body: ResponseBody? = null
    ) = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .message("")
        .code(code)
        .body(body)
        .build()
}

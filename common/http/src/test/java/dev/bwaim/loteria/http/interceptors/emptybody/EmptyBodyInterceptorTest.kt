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

import dev.bwaim.loteria.http.interceptors.emptybody.EmptyBodyInterceptor.Companion.EMPTY_BODY
import dev.bwaim.loteria.http.utils.HttpTestUtils.createFakeResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import okhttp3.Interceptor
import okhttp3.Response
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

internal class EmptyBodyInterceptorTest {
    @RelaxedMockK
    private lateinit var mockChain: Interceptor.Chain

    private lateinit var emptyBodyInterceptor: Interceptor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        emptyBodyInterceptor = EmptyBodyInterceptor()
    }

    @Test
    fun responseContainsEmptyBody_whenInitialResponseBodyIsNull() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_NO_CONTENT))

        val body = emptyBodyInterceptor.intercept(mockChain).body

        Assert.assertEquals(
            EMPTY_BODY,
            body,
        )
    }

    @Test
    fun responseContainsEmptyBody_whenInitialResponseBodyIsEmpty() {
        mockChainProceed(
            createFakeResponse(
                code = HttpURLConnection.HTTP_NO_CONTENT,
                body = mockk(relaxed = true),
            ),
        )

        val body = emptyBodyInterceptor.intercept(mockChain).body

        Assert.assertEquals(
            EMPTY_BODY,
            body,
        )
    }

    @Test
    fun responseCodeIsOk_whenInitialResponseCodeIsNoContent() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_NO_CONTENT))

        val code = emptyBodyInterceptor.intercept(mockChain).code

        Assert.assertEquals(
            HttpURLConnection.HTTP_OK,
            code,
        )
    }

    @Test
    fun responseCodeIsOk_whenInitialResponseCodeIsReset() {
        mockChainProceed(createFakeResponse(code = HttpURLConnection.HTTP_RESET))

        val code = emptyBodyInterceptor.intercept(mockChain).code

        Assert.assertEquals(
            HttpURLConnection.HTTP_OK,
            code,
        )
    }

    @Test
    fun responseUnchanged_whenInitialResponseIsNotSuccessful() {
        val fakeResponse = createFakeResponse(code = HttpURLConnection.HTTP_NOT_FOUND)
        mockChainProceed(fakeResponse)

        val response = emptyBodyInterceptor.intercept(mockChain)

        Assert.assertEquals(
            fakeResponse,
            response,
        )
    }

    @Test
    fun responseUnchanged_whenInitialResponseSuccessful() {
        val fakeResponse = createFakeResponse(code = HttpURLConnection.HTTP_OK)
        mockChainProceed(fakeResponse)

        val response = emptyBodyInterceptor.intercept(mockChain)

        Assert.assertEquals(
            fakeResponse,
            response,
        )
    }

    private fun mockChainProceed(response: Response) {
        every { mockChain.proceed(any()) } returns response
    }
}

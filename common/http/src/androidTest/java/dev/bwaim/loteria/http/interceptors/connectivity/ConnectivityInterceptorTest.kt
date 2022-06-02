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

internal class ConnectivityInterceptorTest {
//    @RelaxedMockK
//    private lateinit var mockConnectivityManager: ConnectivityManager
//
//    @RelaxedMockK
//    private lateinit var mockContext: Context
//
//    private lateinit var connectivityInterceptor: ConnectivityInterceptor
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this)
//
//        every {
//            mockContext.getSystemService<ConnectivityManager>()
//        } returns mockConnectivityManager
//
//        connectivityInterceptor = ConnectivityInterceptor(mockContext)
//    }
//
//    @Test(expected = NoConnectivityException::class)
//    fun noConnectivityExceptionThrown_whenDeviceNotConnectedToInternet() {
//        mockConnectivity(false)
//
//        connectivityInterceptor.intercept(mockk(relaxed = true))
//    }
//
//    @Test
//    fun requestProceeded_whenDeviceConnectedToInternet() {
//        mockConnectivity(true)
//        val fakeRequest = createFakeRequest()
//        val chain = mockk<Interceptor.Chain>(relaxed = true).apply {
//            every { request() } returns fakeRequest
//        }
//
//        connectivityInterceptor.intercept(chain)
//
//        verify {
//            chain.proceed(fakeRequest)
//        }
//    }
//
//    private fun mockConnectivity(connected: Boolean) {
//        if (Build.VERSION.SDK_INT < 24) {
//            every {
//                mockConnectivityManager.activeNetworkInfo
//            } returns mockk<NetworkInfo>(relaxed = true).apply {
//                every { isConnectedOrConnecting } returns connected
//            }
//        } else {
//            val mockNetworkCapabilities = mockk<NetworkCapabilities>()
//            every {
//                mockNetworkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            } returns connected
//            connectivityInterceptor.onCapabilitiesChanged(mockk<Network>(), mockNetworkCapabilities)
//        }
//    }
//
//    private fun createFakeRequest(
//        url: String = "http://test.fr",
//        method: String = "GET",
//        body: RequestBody? = null
//    ) = Request.Builder()
//        .url(url)
//        .method(method, body)
//        .build()
}

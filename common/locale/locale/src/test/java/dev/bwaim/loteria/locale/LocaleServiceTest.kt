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

@file:OptIn(ExperimentalCoroutinesApi::class)

package dev.bwaim.loteria.locale

import app.cash.turbine.test
import dev.bwaim.loteria.test.repository.TestLocaleRepository
import java.util.Locale
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class LocaleServiceTest {
    private lateinit var subject: LocaleService

    @Before
    fun setUp() {
        subject = LocaleService(
            ioDispatcher = UnconfinedTestDispatcher(),
            localeRepository = TestLocaleRepository()
        )
    }

    @Test
    fun localeService_observe_localeChanges() =
        runTest {
            subject.observeLocale().test {
                Assert.assertEquals(Locale("fr"), awaitItem())
                subject.setLocale(Locale("es"))
                Assert.assertEquals(Locale("es"), awaitItem())
                cancel()
            }
        }
}

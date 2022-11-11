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

package dev.bwaim.loteria.locale.impl

import dev.bwaim.loteria.locale.LocaleService
import io.mockk.every
import io.mockk.mockk
import java.util.Locale
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.Assert
import org.junit.Test

internal class LocaleActivityDelegateImplTest {

    @Test
    fun shouldReturnInitialLocale_whenObserved() = runBlocking {
        val defaultValue = Locale("fr")
        val mockLocaleService = mockk<LocaleService>(relaxed = true).apply {
            every { observeLocale() } returns flowOf(defaultValue)
        }

        val localeActivityDelegate = LocaleActivityDelegateImpl(
            applicationScope = TestScope(),
            localeService = mockLocaleService
        )

        val locale = localeActivityDelegate
            .locale
            .first()

        Assert.assertEquals(
            defaultValue,
            locale
        )
    }
}

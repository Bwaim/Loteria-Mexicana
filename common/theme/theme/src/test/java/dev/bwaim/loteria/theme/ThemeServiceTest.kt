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

package dev.bwaim.loteria.theme

import app.cash.turbine.test
import dev.bwaim.loteria.test.repository.TestThemeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ThemeServiceTest {
    private lateinit var subject: ThemeService

    @Before
    fun setUp() {
        subject = ThemeService(
            ioDispatcher = UnconfinedTestDispatcher(),
            themeRepository = TestThemeRepository()
        )
    }

    @Test
    fun themeService_observe_themeChanges() =
        runTest {
            subject.observeTheme().test {
                Assert.assertEquals(Theme.DARK, awaitItem())
                subject.setTheme(Theme.LIGHT)
                Assert.assertEquals(Theme.LIGHT, awaitItem())
                cancel()
            }
        }

    @Test
    fun themeService_getThemes_returnAllThemes() {
        val expectedResult = listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM, Theme.BATTERY_SAVER)

        val result = subject.getThemes()

        Assert.assertEquals(expectedResult, result)
    }
}

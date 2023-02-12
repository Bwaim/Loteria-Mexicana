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

package dev.bwaim.loteria.theme.impl

import app.cash.turbine.test
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.test.android.testThemePreferencesDataStore
import dev.bwaim.loteria.theme.Theme
import io.mockk.every
import io.mockk.mockkObject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

internal class ThemeRepositoryImplTest {
    private lateinit var subject: ThemeRepositoryImpl

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        subject = ThemeRepositoryImpl(tmpFolder.testThemePreferencesDataStore())

        mockkObject(BuildWrapper)
    }

    @Test
    fun firstTheme_isThemePreferenceDefaultValue() = runTest {
        every { BuildWrapper.SDK_INT } returns 29

        Assert.assertEquals(
            ThemeHelper.defaultTheme,
            subject.observeTheme().first(),
        )
    }

    // This test fails on Windows : https://github.com/android/nowinandroid/issues/98
    @Test
    fun observeTheme_outputsThemePreferences() = runTest {
        every { BuildWrapper.SDK_INT } returns 29

        val themeValue = Theme.DARK

        subject.observeTheme().test {
            Assert.assertEquals(
                ThemeHelper.defaultTheme,
                awaitItem(),
            )

            subject.setTheme(themeValue)

            Assert.assertEquals(
                themeValue,
                awaitItem(),
            )

            cancel()
        }
    }
}

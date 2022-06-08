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

import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class ThemeHelperTest {
    @Before
    fun setUp() {
        mockkObject(BuildWrapper)
    }

    @Test
    fun defaultValueWithMaximumApi28_isBatterySaver() {
        every { BuildWrapper.SDK_INT } returns 28

        Assert.assertEquals(
            Theme.BATTERY_SAVER,
            ThemeHelper.defaultTheme
        )
    }

    @Test
    fun defaultValueWithMinimumApi29_isSystem() {
        every { BuildWrapper.SDK_INT } returns 29

        Assert.assertEquals(
            Theme.SYSTEM,
            ThemeHelper.defaultTheme
        )
    }

    @Test
    fun emptyTheme_outputsDefaultValue() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences(String())

        Assert.assertEquals(
            ThemeHelper.defaultTheme,
            theme
        )
    }

    @Test
    fun systemThemeOnMaximumApi28_outputsBatterySaver() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences(Theme.SYSTEM.value)

        Assert.assertEquals(
            Theme.BATTERY_SAVER,
            theme
        )
    }

    @Test
    fun batterySaverThemeOnMinimumApi29_outputsSystem() {
        every { BuildWrapper.SDK_INT } returns 29

        val theme = ThemeHelper.fromPreferences(Theme.BATTERY_SAVER.value)

        Assert.assertEquals(
            Theme.SYSTEM,
            theme
        )
    }
}

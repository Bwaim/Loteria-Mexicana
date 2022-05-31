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

import com.google.common.truth.Truth
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test

internal class ThemeHelperTest {
    @Before
    fun setUp() {
        mockkObject(BuildWrapper)
    }

    @Test
    fun shouldReturnBatterySaver_whenApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        Truth.assertThat(ThemeHelper.defaultTheme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        Truth.assertThat(ThemeHelper.defaultTheme).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun shouldReturnBatterySaver_whenPreferenceIsEmptyAndApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences("")

        Truth.assertThat(theme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenPreferenceIsEmptyAndApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        val theme = ThemeHelper.fromPreferences("")

        Truth.assertThat(theme).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun shouldReturnBatterySaver_whenPreferenceIsSystemAndApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences(Theme.SYSTEM.value)

        Truth.assertThat(theme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenPreferenceIsBatterySaverAndApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        val theme = ThemeHelper.fromPreferences(Theme.BATTERY_SAVER.value)

        Truth.assertThat(theme).isEqualTo(Theme.SYSTEM)
    }
}

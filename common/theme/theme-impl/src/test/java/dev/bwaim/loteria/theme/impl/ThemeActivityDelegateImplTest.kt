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
import dev.bwaim.loteria.theme.ThemeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.Assert
import org.junit.Test

internal class ThemeActivityDelegateImplTest {
    @ExperimentalCoroutinesApi
    @Test
    fun shouldReturnInitialTheme_whenObserved() = runBlocking {
        mockkObject(BuildWrapper)
        every { BuildWrapper.SDK_INT } returns 29
        val mockThemeService = mockk<ThemeService>(relaxed = true).apply {
            every { observeTheme() } returns flowOf(ThemeHelper.defaultTheme)
        }
        val themeActivityDelegate = ThemeActivityDelegateImpl(
            applicationScope = TestScope(),
            themeService = mockThemeService
        )

        val theme = themeActivityDelegate
            .theme
            .first()

        Assert.assertEquals(
            ThemeHelper.defaultTheme,
            theme
        )
    }
}

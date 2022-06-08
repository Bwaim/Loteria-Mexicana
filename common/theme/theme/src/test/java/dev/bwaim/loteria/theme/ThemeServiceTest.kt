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

import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ThemeServiceTest {
    @RelaxedMockK
    private lateinit var themeRepository: ThemeRepository
    private lateinit var themeService: ThemeService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        themeService = ThemeService(
            ioDispatcher = UnconfinedTestDispatcher(),
            themeRepository = themeRepository
        )
    }

    @Test
    fun shouldReturnThemeFlow_whenObserved() = runBlocking {
        val expectedThemes = arrayOf(Theme.SYSTEM, Theme.LIGHT)
        every { themeRepository.observeTheme() } returns flowOf(*expectedThemes)

        val themes = themeService
            .observeTheme()
            .toList()
            .toTypedArray()

        Truth.assertThat(themes).isEqualTo(expectedThemes)
    }

    @Test
    fun shouldSaveTheme_whenSet() = runBlocking {
        val theme = Theme.SYSTEM

        themeService.setTheme(theme)

        coVerify { themeRepository.setTheme(theme) }
    }

    @Test
    fun shouldReturnAllThemes_whenAsked() {
        val expectedResult = listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM, Theme.BATTERY_SAVER)

        val result = themeService.getThemes()

        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}

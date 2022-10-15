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

package dev.bwaim.loteria.settings

import app.cash.turbine.test
import dev.bwaim.loteria.test.MainCoroutineRule
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var settingsViewModel: SettingsViewModel

    @MockK(relaxUnitFun = true)
    lateinit var mockThemeService: ThemeService

    private val themes = listOf(Theme.LIGHT, Theme.DARK)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearMocks(mockThemeService)
    }

    @Test
    fun shouldUpdateState_whenThemeChanged() = runTest {
        initViewModel()

        settingsViewModel.viewState.test {
            Assert.assertEquals(
                SettingsState(),
                awaitItem()
            )
            Assert.assertEquals(
                SettingsState(appTheme = Theme.LIGHT, themes = themes),
                awaitItem()
            )
            Assert.assertEquals(
                SettingsState(appTheme = Theme.DARK, themes = themes),
                awaitItem()
            )
        }
    }

    @Test
    fun setTheme_whenSet() {
        initViewModel()

        settingsViewModel.setTheme(Theme.SYSTEM)

        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { mockThemeService.setTheme(Theme.SYSTEM) }
    }

    private fun initViewModel() {
        every { mockThemeService.getThemes() } returns themes
        every { mockThemeService.observeTheme() } returns flowOf(Theme.LIGHT, Theme.DARK)

        settingsViewModel = SettingsViewModel(mockThemeService)
    }
}

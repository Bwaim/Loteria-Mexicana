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

import dev.bwaim.loteria.locale.LocaleService
import dev.bwaim.loteria.test.MainDispatcherRule
import dev.bwaim.loteria.test.repository.TestThemeRepository
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Locale

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SettingsViewModel

    private val themeService: ThemeService =
        ThemeService(UnconfinedTestDispatcher(), TestThemeRepository())
    private val localeService: LocaleService = LocaleService()

    @Before
    fun setUp() {
        viewModel = SettingsViewModel(
            themeService = themeService,
            localeService = localeService,
        )
    }

    @Test
    fun stateIsInitiallyDefaultValue() = runTest {
        assertEquals(
            SettingsState(),
            viewModel.viewState.value,
        )
    }

    @Test
    fun stateHasSavedValueAfterLoading() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

        assertEquals(
            SettingsState(
                appTheme = Theme.DARK,
                themes = listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM, Theme.BATTERY_SAVER),
                availableLocales = listOf(
                    Locale("es"),
                    Locale.ENGLISH,
                    Locale.FRENCH,
                ),
            ),
            viewModel.viewState.value,
        )

        collectJob.cancel()
    }

    @Test
    fun themeUpdatesAfterChangingTheme() = runTest {
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.viewState.collect() }

        assertEquals(
            Theme.DARK,
            viewModel.viewState.value.appTheme,
        )

        viewModel.setTheme(Theme.SYSTEM)

        assertEquals(
            Theme.SYSTEM,
            viewModel.viewState.value.appTheme,
        )

        collectJob.cancel()
    }
}

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

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import dev.bwaim.loteria.theme.Theme
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SettingsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var mockSettingsViewModel: SettingsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldShowSettings() {
        val themes = listOf(Theme.LIGHT, Theme.DARK)
        var themeTitle: String? = null
        var currentThemeLabel: String? = null

        every { mockSettingsViewModel.viewState } returns MutableStateFlow(
            SettingsState(
                appTheme = Theme.LIGHT,
                themes = themes
            )
        )

        testContent {
            Settings(
                viewModel = mockSettingsViewModel,
                navigateUp = { }
            )
            themeTitle = stringResource(id = R.string.settings_app_theme_title)
            currentThemeLabel = Theme.LIGHT.getLabel()
        }
        composeTestRule.onNode(hasText(themeTitle!!)).assertExists()
        composeTestRule.onNode(hasText(currentThemeLabel!!)).assertExists()
    }

    private fun testContent(call: @Composable () -> Unit) {
        composeTestRule.setContent {
            LoteriaTheme {
                call()
            }
        }
    }
}

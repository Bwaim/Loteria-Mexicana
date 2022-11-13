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

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.bwaim.loteria.theme.Theme
import java.util.Locale
import org.junit.Rule
import org.junit.Test

internal class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun themeIsVisibleWhenLoaded() {
        composeTestRule.setContent {
            BoxWithConstraints {
                SettingsScreen(
                    viewState = SettingsState(
                        appTheme = Theme.LIGHT,
                        themes = themes,
                        availableLocales = applicationLocales
                    ),
                    onBackClick = { },
                    onThemeChanged = { },
                    onLocaleChange = { }
                )
            }
        }

        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.resources.getString(R.string.settings_app_theme_light)
            )
            .assertExists()
    }

    private val themes = listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM, Theme.BATTERY_SAVER)
    private val applicationLocales = listOf(
        Locale("es"),
        Locale.ENGLISH,
        Locale.FRENCH
    )
}

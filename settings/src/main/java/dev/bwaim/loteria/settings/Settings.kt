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

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.design.preference.model.ListPreferenceValues
import dev.bwaim.loteria.compose.design.preference.model.Preference
import dev.bwaim.loteria.compose.design.preference.ui.ListPreferenceWidget
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme

private typealias SettingsActioner = (SettingsAction) -> Unit

@Composable
public fun Settings(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Settings(viewState) { action ->
        when (action) {
            NavigateUp -> navigateUp()
            is OnThemeChanged -> {
                viewModel.setTheme(action.value)
            }
        }
    }
}

@Composable
private fun Settings(
    viewState: SettingsState,
    actioner: SettingsActioner
) {
    val appTheme = viewState.appTheme.toPreference()
    val themesPreferences =
        viewState.themes.toListPreferences(stringResource(id = R.string.settings_app_theme_title))

    Scaffold(
        topBar = { SettingsAppBar(actioner) }
    ) { contentPadding ->
        ListPreferenceWidget(
            modifier = Modifier.padding(contentPadding),
            preferences = themesPreferences,
            currentValue = appTheme,
            onValueChanged = { actioner(OnThemeChanged(it.value as Theme)) }
        )
    }
}

@Composable
private fun SettingsAppBar(actioner: SettingsActioner) {
    SmallTopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.settings_title)) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        ),
        navigationIcon = {
            BackButton {
                actioner(NavigateUp)
            }
        }
    )
}

@Composable
@VisibleForTesting
public fun Theme.getLabel(): String =
    when (this) {
        Theme.LIGHT -> stringResource(id = R.string.settings_app_theme_light)
        Theme.DARK -> stringResource(id = R.string.settings_app_theme_dark)
        Theme.SYSTEM -> stringResource(id = R.string.settings_app_theme_system)
        Theme.BATTERY_SAVER -> stringResource(id = R.string.settings_app_theme_battery)
    }

@Composable
private fun Theme.toPreference(): Preference<Theme> =
    Preference(label = this.getLabel(), value = this)

@Composable
private fun List<Theme>.toListPreferences(title: String): ListPreferenceValues<Theme> =
    ListPreferenceValues(
        title = title,
        entries = this
            .filter { it.isAvailable() }
            .associate {
                it.getLabel() to it.toPreference()
            }
    )

private fun Theme.isAvailable(): Boolean =
    when (this) {
        Theme.LIGHT -> true
        Theme.DARK -> true
        Theme.SYSTEM -> BuildWrapper.isAtLeastQ
        Theme.BATTERY_SAVER -> !BuildWrapper.isAtLeastQ
    }

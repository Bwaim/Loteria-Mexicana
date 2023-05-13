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
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.bwaim.loteria.compose.BackButton
import dev.bwaim.loteria.compose.TopAppBarTitle
import dev.bwaim.loteria.compose.design.preference.model.ListPreferenceValues
import dev.bwaim.loteria.compose.design.preference.model.Preference
import dev.bwaim.loteria.compose.design.preference.ui.ListPreferenceWidget
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme
import java.util.Locale

@Composable
public fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SettingsScreen(
        viewState,
        onBackClick = onBackClick,
        onThemeChanged = viewModel::setTheme,
        onLocaleChange = { AppCompatDelegate.setApplicationLocales(LocaleListCompat.create(it)) },
    )
}

@Composable
internal fun SettingsScreen(
    viewState: SettingsState,
    onBackClick: () -> Unit,
    onThemeChanged: (Theme) -> Unit,
    onLocaleChange: (Locale) -> Unit,
) {
    val appTheme = viewState.appTheme.toPreference()
    val themesPreferences = viewState.themes.toThemeListPreferences()
    val localesPreferences = viewState.availableLocales.toLocaleListPreferences()

    Scaffold(
        topBar = { SettingsAppBar(onBackClick) },
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
        ) {
            ListPreferenceWidget(
                preferences = themesPreferences,
                currentValue = appTheme,
                onValueChanged = { onThemeChanged(it.value as Theme) },
            )

            ListPreferenceWidget(
                preferences = localesPreferences,
                currentValue = getCurrentLocale(),
                onValueChanged = { onLocaleChange(it.value as Locale) },
            )
        }
    }
}

@Composable
private fun SettingsAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = { TopAppBarTitle(text = stringResource(id = R.string.settings_title)) },
        navigationIcon = {
            BackButton { onBackClick() }
        },
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
private fun List<Theme>.toThemeListPreferences(): ListPreferenceValues<Theme> =
    ListPreferenceValues(
        title = stringResource(id = R.string.settings_app_theme_title),
        entries = this
            .filter { it.isAvailable() }
            .associate {
                it.getLabel() to it.toPreference()
            },
    )

private fun Theme.isAvailable(): Boolean =
    when (this) {
        Theme.LIGHT -> true
        Theme.DARK -> true
        Theme.SYSTEM -> BuildWrapper.isAtLeastQ
        Theme.BATTERY_SAVER -> !BuildWrapper.isAtLeastQ
    }

@Composable
private fun getCurrentLocale(): Preference<Locale> =
    (AppCompatDelegate.getApplicationLocales()[0] ?: Locale.getDefault()).toPreference()

@Composable
private fun List<Locale>.toLocaleListPreferences(): ListPreferenceValues<Locale> {
    val applicationLocales = LocaleListCompat.create(
        *(this.toTypedArray()),
    )
    val locales: MutableMap<String, Preference<Locale>> = mutableMapOf()
    for (i in 0 until applicationLocales.size()) {
        with(applicationLocales[i]) {
            this?.run {
                locales.put(getDisplayLanguage(this), this.toPreference())
            }
        }
    }
    return ListPreferenceValues(
        title = stringResource(id = R.string.settings_language_title),
        entries = locales,
    )
}

private fun Locale.toPreference(): Preference<Locale> =
    Preference(label = getDisplayLanguage(this), value = this)

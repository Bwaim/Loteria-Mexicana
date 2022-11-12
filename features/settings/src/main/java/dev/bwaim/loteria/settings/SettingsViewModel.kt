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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.bwaim.loteria.locale.LocaleService
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
public class SettingsViewModel @Inject constructor(
    localeService: LocaleService,
    private val themeService: ThemeService
) : ViewModel() {

    public val viewState: StateFlow<SettingsState> = combine(
        themeService.observeTheme(),
        flowOf(themeService.getThemes()),
        flowOf(localeService.getLocales())
    ) { appTheme, themes, locales ->
        SettingsState(
            appTheme = appTheme,
            themes = themes,
            availableLocales = locales
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SettingsState())

    public fun setTheme(theme: Theme) {
        viewModelScope.launch {
            themeService.setTheme(theme)
        }
    }
}

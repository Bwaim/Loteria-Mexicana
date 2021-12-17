package dev.bwaim.loteria.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class SettingsViewModel @Inject constructor(
    private val themeService: ThemeService
) : ViewModel() {

    public val viewState: StateFlow<SettingsState> = combine(
        themeService.observeTheme(),
        flowOf(themeService.getThemes())
    ) { appTheme, themes ->
        SettingsState(
            appTheme = appTheme,
            themes = themes
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SettingsState())

    public fun setTheme(theme: Theme) {
        viewModelScope.launch {
            themeService.setTheme(theme)
        }
    }
}

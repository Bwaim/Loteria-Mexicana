package dev.bwaim.loteria.settings

import dev.bwaim.loteria.theme.Theme

public data class SettingsState(
    val appTheme: Theme = Theme.LIGHT,
    val themes: List<Theme> = emptyList()
)

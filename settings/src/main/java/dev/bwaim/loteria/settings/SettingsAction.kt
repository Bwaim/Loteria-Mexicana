package dev.bwaim.loteria.settings

import dev.bwaim.loteria.theme.Theme

internal sealed interface SettingsAction
internal object NavigateUp : SettingsAction
internal data class OnThemeChanged(val value: Theme) : SettingsAction

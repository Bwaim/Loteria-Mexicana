package dev.bwaim.loteria.theme.impl

import androidx.annotation.VisibleForTesting
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme

@VisibleForTesting
public object ThemeHelper {
    @get:VisibleForTesting
    public val defaultTheme: Theme
        get() = if (BuildWrapper.isAtLeastQ) {
            Theme.SYSTEM
        } else {
            Theme.BATTERY_SAVER
        }

    @VisibleForTesting
    public fun fromPreferences(value: String): Theme {
        return when {
            value.isEmpty() -> defaultTheme
            value == Theme.SYSTEM.value && !BuildWrapper.isAtLeastQ -> Theme.BATTERY_SAVER
            value == Theme.BATTERY_SAVER.value && BuildWrapper.isAtLeastQ -> Theme.SYSTEM
            else -> Theme.from(value)
        }
    }
}

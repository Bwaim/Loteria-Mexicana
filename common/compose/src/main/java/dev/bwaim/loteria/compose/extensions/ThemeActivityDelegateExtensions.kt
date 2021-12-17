package dev.bwaim.loteria.compose.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dev.bwaim.loteria.compose.collectAsStateWithLifecycle
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeActivityDelegate

@Composable
public fun ThemeActivityDelegate.shouldUseDarkColors(): Boolean {
    val theme by theme.collectAsStateWithLifecycle()
    return when (theme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        else -> isSystemInDarkTheme()
    }
}

package dev.bwaim.loteria.theme

import kotlinx.coroutines.flow.StateFlow

public interface ThemeActivityDelegate {
    public val theme: StateFlow<Theme>
}

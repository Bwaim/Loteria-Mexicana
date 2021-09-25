package dev.bwaim.loteria.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@Composable
public fun LoteriaTheme(
    useDarkColors: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (useDarkColors) LoteriaDarkColors else LoteriaLightColors,
        typography = debugTypography(),
        shapes = Shapes,
        content = content
    )
}

public object LoteriaTheme {
    public val typography: LoteriaTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalLoteriaTypography.current
}

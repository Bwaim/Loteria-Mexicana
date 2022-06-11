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

package dev.bwaim.loteria.compose.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val LightAndroidColorScheme = lightColorScheme(
    primary = DarkBronze40,
    onPrimary = Color.White,
    primaryContainer = DarkBronze90,
    onPrimaryContainer = DarkBronze10,
    secondary = BangladeshGreen40,
    onSecondary = Color.White,
    secondaryContainer = BangladeshGreen90,
    onSecondaryContainer = BangladeshGreen10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Wenge99,
    onBackground = Wenge10,
    surface = Wenge99,
    onSurface = Wenge10,
    surfaceVariant = BlackCoral90,
    onSurfaceVariant = BlackCoral30,
    outline = BlackCoral50
)

private val DarkAndroidColorScheme = darkColorScheme(
    primary = DarkBronze80,
    onPrimary = DarkBronze20,
    primaryContainer = DarkBronze30,
    onPrimaryContainer = DarkBronze90,
    secondary = BangladeshGreen80,
    onSecondary = BangladeshGreen20,
    secondaryContainer = BangladeshGreen30,
    onSecondaryContainer = BangladeshGreen90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    errorContainer = Red20,
    onError = Red30,
    onErrorContainer = Red90,
    background = Wenge10,
    onBackground = Wenge90,
    surface = Wenge10,
    onSurface = Wenge80,
    surfaceVariant = BlackCoral30,
    onSurfaceVariant = BlackCoral80,
    outline = BlackCoral60
)

@Composable
fun LoteriaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    androidTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        androidTheme && darkTheme -> DarkAndroidColorScheme
        androidTheme -> LightAndroidColorScheme
        darkTheme -> DarkAndroidColorScheme // DarkDefaultColorScheme
        else -> LightAndroidColorScheme // LightDefaultColorScheme
    }

    val backgroundTheme = when {
        androidTheme && darkTheme -> BackgroundTheme(
            color = Color.Black
        )
        androidTheme -> BackgroundTheme(
            color = Wenge95
        )
        darkTheme -> BackgroundTheme(
            color = colorScheme.surface,
            tonalElevation = 2.dp
        )
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> BackgroundTheme(
            color = colorScheme.surface,
            tonalElevation = 2.dp,
            primaryGradientColor = colorScheme.primary.lighten(0.95f),
            secondaryGradientColor = colorScheme.secondary.lighten(0.95f),
            tertiaryGradientColor = colorScheme.tertiary.lighten(0.95f),
            neutralGradientColor = colorScheme.surface.lighten(0.95f)
        )
        else -> BackgroundTheme(
            color = colorScheme.surface,
            tonalElevation = 2.dp,
            primaryGradientColor = Purple95,
            secondaryGradientColor = Orange95,
            tertiaryGradientColor = Blue95,
            neutralGradientColor = DarkPurpleGray95
        )
    }
    CompositionLocalProvider(LocalBackgroundTheme provides backgroundTheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = LoteriaTypography,
            content = content
        )
    }
}

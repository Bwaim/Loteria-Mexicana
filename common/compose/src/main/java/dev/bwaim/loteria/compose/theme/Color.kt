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

import androidx.compose.material.Colors
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp

// Light color palette
private val PrimaryLight = Color(0xffffb74d)
private val PrimaryLightLight = Color(0xffffe97d)
private val PrimaryDarkLight = Color(0xffc88719)
private val OnPrimaryLight = Color(0xbf000000)

private val SecondaryLight = Color(0xff4dd0e1)
private val SecondaryLightLight = Color(0xff88ffff)
private val SecondaryDarkLight = Color(0xff009faf)
private val OnSecondaryLight = Color(0xbf000000)

private val BackgroundLight = Color(0xffffffff)
private val OnBackgroundLight = Color(0xff212121)

private val SurfaceLight = Color(0xffffffff)
private val OnSurfaceLight = Color(0xff212121)

private val ErrorLight = Color(0xffb00020)
private val OnErrorLight = Color(0xffffffff)

// Dark color palette
private val PrimaryDark = Color(0xff7b78d9)
private val PrimaryLightDark = Color(0xff6c69bf)
private val PrimaryDarkDark = Color(0xff8f8dd9)
private val OnPrimaryDark = Color(0xfffcfcfe)

private val SecondaryDark = Color(0xffc161d6)
private val SecondaryLightDark = Color(0xffaa55bd)
private val SecondaryDarkDark = Color(0xffc576d6)
private val OnSecondaryDark = Color(0xfffcfcfe)

private val BackgroundDark = Color(0xff0c0b28)
private val OnBackgroundDark = Color(0xfffcfcfe)

private val SurfaceDark = Color(0xff0c0b28)
private val OnSurfaceDark = Color(0xfffcfcfe)

private val ErrorDark = Color(0xffa93232)
private val OnErrorDark = Color(0xfffcfcfe)

public val LoteriaLightColors: Colors = lightColors(
    primary = PrimaryLight,
    primaryVariant = PrimaryDarkLight,
    secondary = SecondaryLight,
    secondaryVariant = SecondaryDarkLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = ErrorLight,
    onPrimary = OnPrimaryLight,
    onSecondary = OnSecondaryLight,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight,
    onError = OnErrorLight
)

public val LoteriaDarkColors: Colors = darkColors(
    primary = PrimaryDark,
    primaryVariant = PrimaryDarkDark,
    secondary = SecondaryDark,
    secondaryVariant = SecondaryDarkDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = ErrorDark,
    onPrimary = OnPrimaryDark,
    onSecondary = OnSecondaryDark,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark,
    onError = OnErrorDark
)

public val Colors.primaryLight: Color
    @Composable
    get() = if (isLight) PrimaryLightLight else PrimaryLightDark

public val Colors.secondaryLight: Color
    @Composable
    get() = if (isLight) SecondaryLightLight else SecondaryLightDark

/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
public fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

@Composable
public fun Colors.elevatedSurface(elevation: Dp): Color {
    return LocalElevationOverlay.current?.apply(
        color = this.surface,
        elevation = elevation
    ) ?: this.surface
}

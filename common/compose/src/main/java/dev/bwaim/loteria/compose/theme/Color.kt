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

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import kotlin.math.roundToInt

val BangladeshGreen10 = Color(0xFF002113)
val BangladeshGreen20 = Color(0xFF003823)
val BangladeshGreen30 = Color(0xFF005236)
val BangladeshGreen40 = Color(0xFF006C49)
val BangladeshGreen80 = Color(0xFF6EDBAA)
val BangladeshGreen90 = Color(0xFF8AF7C4)
val BangladeshGreen95 = Color(0xFFBBFFDD)
val BlackCoral10 = Color(0xFF221A10)
val BlackCoral20 = Color(0xFF382F24)
val BlackCoral30 = Color(0xFF4F4539)
val BlackCoral40 = Color(0xFF685D50)
val BlackCoral50 = Color(0xFF817568)
val BlackCoral60 = Color(0xFF9C8F80)
val BlackCoral80 = Color(0xFFD3C4B5)
val BlackCoral90 = Color(0xFFF0E0D0)
val BlackCoral95 = Color(0xFFFFEEDD)
val Blue10 = Color(0xFF0F1D24)
val Blue20 = Color(0xFF003547)
val Blue30 = Color(0xFF004D65)
val Blue40 = Color(0xFF006684)
val Blue80 = Color(0xFF61D3FF)
val Blue90 = Color(0xFFBAE9FF)
val Blue95 = Color(0xFFDEF4FF)
val DarkBronze10 = Color(0xFF2A1800)
val DarkBronze20 = Color(0xFF462B00)
val DarkBronze30 = Color(0xFF643F00)
val DarkBronze40 = Color(0xFF835400)
val DarkBronze80 = Color(0xFFFFB94E)
val DarkBronze90 = Color(0xFFFFDDB1)
val DarkBronze95 = Color(0xFFFFEEDA)
val DarkPurpleGray95 = Color(0xFFFAEEEF)
val Orange95 = Color(0xFFFFEDE6)
val Purple95 = Color(0xFFFFEBFB)
val Red10 = Color(0xFF410001)
val Red20 = Color(0xFF680003)
val Red30 = Color(0xFF930006)
val Red40 = Color(0xFFBA1B1B)
val Red80 = Color(0xFFFFB4A9)
val Red90 = Color(0xFFFFDAD4)
val Wenge10 = Color(0xFF1F1B16)
val Wenge20 = Color(0xFF35302A)
val Wenge30 = Color(0xFF4B4640)
val Wenge40 = Color(0xFF645D57)
val Wenge80 = Color(0xFFCEC5BD)
val Wenge90 = Color(0xFFEBE1D9)
val Wenge95 = Color(0xFFFFEEDD)
val Wenge99 = Color(0xFFFCFCFC)

internal fun Color.lighten(luminance: Float): Color {
    val hsl = FloatArray(3)
    ColorUtils.RGBToHSL(
        (red * 256).roundToInt(),
        (green * 256).roundToInt(),
        (blue * 256).roundToInt(),
        hsl
    )
    hsl[2] = luminance
    return Color(ColorUtils.HSLToColor(hsl))
}

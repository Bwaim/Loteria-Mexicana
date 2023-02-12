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

internal val BangladeshGreen10 = Color(0xFF002113)
internal val BangladeshGreen20 = Color(0xFF003823)
internal val BangladeshGreen30 = Color(0xFF005236)
internal val BangladeshGreen40 = Color(0xFF006C49)
internal val BangladeshGreen80 = Color(0xFF6EDBAA)
internal val BangladeshGreen90 = Color(0xFF8AF7C4)
internal val BangladeshGreen95 = Color(0xFFBBFFDD)
internal val BlackCoral10 = Color(0xFF221A10)
internal val BlackCoral20 = Color(0xFF382F24)
internal val BlackCoral30 = Color(0xFF4F4539)
internal val BlackCoral40 = Color(0xFF685D50)
internal val BlackCoral50 = Color(0xFF817568)
internal val BlackCoral60 = Color(0xFF9C8F80)
internal val BlackCoral80 = Color(0xFFD3C4B5)
internal val BlackCoral90 = Color(0xFFF0E0D0)
internal val BlackCoral95 = Color(0xFFFFEEDD)
internal val Blue10 = Color(0xFF0F1D24)
internal val Blue20 = Color(0xFF003547)
internal val Blue30 = Color(0xFF004D65)
internal val Blue40 = Color(0xFF006684)
internal val Blue80 = Color(0xFF61D3FF)
internal val Blue90 = Color(0xFFBAE9FF)
internal val Blue95 = Color(0xFFDEF4FF)
internal val DarkBronze10 = Color(0xFF2A1800)
internal val DarkBronze20 = Color(0xFF462B00)
internal val DarkBronze30 = Color(0xFF643F00)
internal val DarkBronze40 = Color(0xFF835400)
internal val DarkBronze80 = Color(0xFFFFB94E)
internal val DarkBronze90 = Color(0xFFFFDDB1)
internal val DarkBronze95 = Color(0xFFFFEEDA)
internal val DarkPurpleGray95 = Color(0xFFFAEEEF)
internal val Orange95 = Color(0xFFFFEDE6)
internal val Purple95 = Color(0xFFFFEBFB)
internal val Red10 = Color(0xFF410001)
internal val Red20 = Color(0xFF680003)
internal val Red30 = Color(0xFF930006)
internal val Red40 = Color(0xFFBA1B1B)
internal val Red80 = Color(0xFFFFB4A9)
internal val Red90 = Color(0xFFFFDAD4)
internal val Wenge10 = Color(0xFF1F1B16)
internal val Wenge20 = Color(0xFF35302A)
internal val Wenge30 = Color(0xFF4B4640)
internal val Wenge40 = Color(0xFF645D57)
internal val Wenge80 = Color(0xFFCEC5BD)
internal val Wenge90 = Color(0xFFEBE1D9)
internal val Wenge95 = Color(0xFFFFEEDD)
internal val Wenge99 = Color(0xFFFCFCFC)

internal fun Color.lighten(luminance: Float): Color {
    val hsl = FloatArray(3)
    ColorUtils.RGBToHSL(
        (red * 256).roundToInt(),
        (green * 256).roundToInt(),
        (blue * 256).roundToInt(),
        hsl,
    )
    hsl[2] = luminance
    return Color(ColorUtils.HSLToColor(hsl))
}

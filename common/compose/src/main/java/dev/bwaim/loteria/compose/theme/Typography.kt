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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
public class LoteriaTypography internal constructor(
    public val headline1: TextStyle,
    public val headline2: TextStyle,
    public val title1: TextStyle,
    public val title2: TextStyle,
    public val subtitle: TextStyle,
    public val subtitleLink: TextStyle,
    public val body: TextStyle,
    public val bodyLink: TextStyle,
    public val button: TextStyle,
    public val mention: TextStyle,
    public val mentionLink: TextStyle,
    public val caption: TextStyle
) {
    public constructor(
        defaultFontFamily: FontFamily = FontFamily.Default,
        headline1: TextStyle = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 40.sp
        ),
        headline2: TextStyle = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 40.sp
        ),
        title1: TextStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 32.sp
        ),
        title2: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            lineHeight = 28.sp
        ),
        subtitle: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 28.sp
        ),
        subtitleLink: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.02.em,
            lineHeight = 28.sp
        ),
        body: TextStyle = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 24.sp
        ),
        bodyLink: TextStyle = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.02.em,
            lineHeight = 24.sp
        ),
        button: TextStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.02.em,
            lineHeight = 24.sp
        ),
        mention: TextStyle = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.sp,
            lineHeight = 16.sp
        ),
        mentionLink: TextStyle = TextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.02.em,
            lineHeight = 16.sp
        ),
        caption: TextStyle = TextStyle(
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.01.em,
            lineHeight = 12.sp
        )
    ) : this(
        headline1 = headline1.withDefaultFontFamily(defaultFontFamily),
        headline2 = headline2.withDefaultFontFamily(defaultFontFamily),
        title1 = title1.withDefaultFontFamily(defaultFontFamily),
        title2 = title2.withDefaultFontFamily(defaultFontFamily),
        subtitle = subtitle.withDefaultFontFamily(defaultFontFamily),
        subtitleLink = subtitleLink.withDefaultFontFamily(defaultFontFamily),
        body = body.withDefaultFontFamily(defaultFontFamily),
        bodyLink = bodyLink.withDefaultFontFamily(defaultFontFamily),
        button = button.withDefaultFontFamily(defaultFontFamily),
        mention = mention.withDefaultFontFamily(defaultFontFamily),
        mentionLink = mentionLink.withDefaultFontFamily(defaultFontFamily),
        caption = caption.withDefaultFontFamily(defaultFontFamily)
    )

    public fun copy(
        headline1: TextStyle = this.headline1,
        headline2: TextStyle = this.headline2,
        title1: TextStyle = this.title1,
        title2: TextStyle = this.title2,
        subtitle: TextStyle = this.subtitle,
        subtitleLink: TextStyle = this.subtitleLink,
        body: TextStyle = this.body,
        bodyLink: TextStyle = this.bodyLink,
        button: TextStyle = this.button,
        mention: TextStyle = this.mention,
        mentionLink: TextStyle = this.mentionLink,
        caption: TextStyle = this.caption,
    ): LoteriaTypography = LoteriaTypography(
        headline1 = headline1,
        headline2 = headline2,
        title1 = title1,
        title2 = title2,
        subtitle = subtitle,
        subtitleLink = subtitleLink,
        body = body,
        bodyLink = bodyLink,
        button = button,
        mention = mention,
        mentionLink = mentionLink,
        caption = caption
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LoteriaTypography) return false

        if (headline1 != other.headline1) return false
        if (headline2 != other.headline2) return false
        if (title1 != other.title1) return false
        if (title2 != other.title2) return false
        if (subtitle != other.subtitle) return false
        if (subtitleLink != other.subtitleLink) return false
        if (body != other.body) return false
        if (bodyLink != other.bodyLink) return false
        if (button != other.button) return false
        if (mention != other.mention) return false
        if (mentionLink != other.mentionLink) return false
        if (caption != other.caption) return false

        return true
    }

    override fun hashCode(): Int {
        var result = headline1.hashCode()
        result = 31 * result + headline2.hashCode()
        result = 31 * result + title1.hashCode()
        result = 31 * result + title2.hashCode()
        result = 31 * result + subtitle.hashCode()
        result = 31 * result + subtitleLink.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + bodyLink.hashCode()
        result = 31 * result + button.hashCode()
        result = 31 * result + mention.hashCode()
        result = 31 * result + mentionLink.hashCode()
        result = 31 * result + caption.hashCode()
        return result
    }

    override fun toString(): String {
        return "Typography(headline1=$headline1, headline2=$headline2, title1=$title1" +
            ", title2=$title2, subtitle=$subtitle, subtitleLink=$subtitleLink, body=$body, " +
            "bodyLink=$bodyLink, button=$button, mention=$mention, " +
            "mentionLink=$mentionLink, caption=$caption)"
    }
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

internal val LocalLoteriaTypography = staticCompositionLocalOf { LoteriaTypography() }

/**
 * A Material [Typography] implementation which sets all text styles to a [TextStyle] with a
 * 1sp font size and a transparent color to discourage usage of
 * [MaterialTheme.typography] in preference to [LoteriaTheme.typography].
 */
internal fun debugTypography(): Typography {
    val debugTextStyle = TextStyle(fontSize = 100.sp)
    return Typography(
        h1 = debugTextStyle,
        h2 = debugTextStyle,
        h3 = debugTextStyle,
        h4 = debugTextStyle,
        h5 = debugTextStyle,
        h6 = debugTextStyle,
        subtitle1 = debugTextStyle,
        subtitle2 = debugTextStyle,
        body1 = debugTextStyle,
        body2 = debugTextStyle,
        button = debugTextStyle,
        caption = debugTextStyle,
        overline = debugTextStyle,
    )
}

@Preview(name = "Typography")
@Composable
private fun PreviewTypography() {
    LoteriaTheme(useDarkColors = false) {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Headline1", style = LoteriaTheme.typography.headline1)
                Text(text = "Headline2", style = LoteriaTheme.typography.headline2)
                Text(text = "Title 1", style = LoteriaTheme.typography.title1)
                Text(text = "Title 2", style = LoteriaTheme.typography.title2)
                Text(text = "Subtitle", style = LoteriaTheme.typography.subtitle)
                Text(text = "Subtitle link", style = LoteriaTheme.typography.subtitleLink)
                Text(text = "Body", style = LoteriaTheme.typography.body)
                Text(text = "Body link", style = LoteriaTheme.typography.bodyLink)
                Text(text = "Button", style = LoteriaTheme.typography.button)
                Text(text = "Mention", style = LoteriaTheme.typography.mention)
                Text(text = "Mention link", style = LoteriaTheme.typography.mentionLink)
                Text(text = "Caption", style = LoteriaTheme.typography.caption)
            }
        }
    }
}

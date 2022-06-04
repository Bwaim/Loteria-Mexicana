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

package dev.bwaim.loteria.theme.impl

import androidx.datastore.core.CorruptionException
import com.google.common.truth.Truth
import dev.bwaim.loteria.theme.Theme
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class ThemePreferencesSerializerTest {

    private val themePreferencesSerializer = ThemePreferencesSerializer()

    @Test
    fun defaultThemePreferences_isEmpty() {
        Truth
            .assertThat(
                themePreferences {
                    // Default value
                }
            )
            .isEqualTo(themePreferencesSerializer.defaultValue)
    }

    @Test
    fun writingAndReadingThemePreferences_outputsCorrectValue() = runTest {
        val expectedThemePreferences = themePreferences {
            theme = Theme.DARK.value
        }

        val outputStream = ByteArrayOutputStream()

        expectedThemePreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val actualThemePreferences = themePreferencesSerializer.readFrom(inputStream)

        Truth
            .assertThat(actualThemePreferences)
            .isEqualTo(expectedThemePreferences)
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidThemePreferences_throwsCorruptionException() = runTest {
        themePreferencesSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}

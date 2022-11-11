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

package dev.bwaim.loteria.locale.impl

import androidx.datastore.core.CorruptionException
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Locale
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class LocalePreferencesSerializerTest {

    private val localePreferencesSerializer = LocalePreferencesSerializer()

    @Test
    fun defaultLocalePreferences_isEmpty() {
        Assert.assertEquals(
            localePreferencesSerializer.defaultValue,
            localePreferences {
                // Default value
            }
        )
    }

    @Test
    fun writingAndReadingLocalePreferences_outputsCorrectValue() = runTest {
        val expectedLocalePreferences = localePreferences {
            locale = Locale.ENGLISH.language
        }

        val outputStream = ByteArrayOutputStream()

        expectedLocalePreferences.writeTo(outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

        val actualLocalePreferences = localePreferencesSerializer.readFrom(inputStream)

        Assert.assertEquals(
            expectedLocalePreferences,
            actualLocalePreferences
        )
    }

    @Test(expected = CorruptionException::class)
    fun readingInvalidLocalePreferences_throwsCorruptionException() = runTest {
        localePreferencesSerializer.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }
}

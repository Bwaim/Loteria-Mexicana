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
import com.google.protobuf.InvalidProtocolBufferException
import dev.bwaim.loteria.theme.Theme
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.unmockkStatic
import io.mockk.verify
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class ThemePreferencesSerializerTest {

    private val themePreferencesSerializer = ThemePreferencesSerializer()

    @Before
    fun setUp() {
        mockkStatic(ThemePreferences::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(ThemePreferences::class)
    }

    @Test
    fun shouldReturnDefaultInstance_whenDefaultValueAsked() {
        val defaultValue = themePreferencesSerializer.defaultValue

        Truth.assertThat(defaultValue).isEqualTo(ThemePreferences.getDefaultInstance())
    }

    @Test
    fun shouldReturnThemePreferences_whenDeserializingSucceeds() = runBlocking {
        val expectedThemePreferences = ThemePreferences
            .newBuilder()
            .setTheme(Theme.SYSTEM.value)
            .build()
        every { ThemePreferences.parseFrom(any<InputStream>()) } returns expectedThemePreferences

        val theme = themePreferencesSerializer.readFrom(mockk())

        Truth.assertThat(theme).isEqualTo(expectedThemePreferences)
    }

    @Test(expected = CorruptionException::class)
    fun shouldThrowCorruptionException_whenDeserializingFails(): Unit = runBlocking {
        every {
            ThemePreferences.parseFrom(any<InputStream>())
        } throws InvalidProtocolBufferException("")

        themePreferencesSerializer.readFrom(mockk())
    }

    @Test
    fun shouldWriteToOutputStream_whenSerialisingThemePreferences() = runBlocking {
        val themePreferences = spyk(ThemePreferences.getDefaultInstance())
        val outputStream = mockk<OutputStream>()

        themePreferencesSerializer.writeTo(themePreferences, outputStream)

        verify { themePreferences.writeTo(outputStream) }
    }
}

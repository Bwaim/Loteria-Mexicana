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
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.io.OutputStream

internal class ThemePreferencesSerializerTest {
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
        val defaultValue = ThemePreferencesSerializer.defaultValue

        Truth.assertThat(defaultValue).isEqualTo(ThemePreferences.getDefaultInstance())
    }

    @Test
    fun shouldReturnThemePreferences_whenDeserializingSucceeds() = runBlocking {
        val expectedThemePreferences = ThemePreferences
            .newBuilder()
            .setTheme(Theme.SYSTEM.value)
            .build()
        every { ThemePreferences.parseFrom(any<InputStream>()) } returns expectedThemePreferences

        val theme = ThemePreferencesSerializer.readFrom(mockk())

        Truth.assertThat(theme).isEqualTo(expectedThemePreferences)
    }

    @Test(expected = CorruptionException::class)
    fun shouldThrowCorruptionException_whenDeserializingFails(): Unit = runBlocking {
        every {
            ThemePreferences.parseFrom(any<InputStream>())
        } throws InvalidProtocolBufferException("")

        ThemePreferencesSerializer.readFrom(mockk())
    }

    @Test
    fun shouldWriteToOutputStream_whenSerialisingThemePreferences() = runBlocking {
        val themePreferences = spyk(ThemePreferences.getDefaultInstance())
        val outputStream = mockk<OutputStream>()

        ThemePreferencesSerializer.writeTo(themePreferences, outputStream)

        verify { themePreferences.writeTo(outputStream) }
    }
}

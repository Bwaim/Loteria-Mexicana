package dev.bwaim.loteria.theme.impl

import android.content.Context
import androidx.datastore.core.DataStore
import com.google.common.truth.Truth
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkObject
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class ThemeRepositoryImplTest {
    @RelaxedMockK
    private lateinit var mockContext: Context

    @RelaxedMockK
    private lateinit var mockDataStore: DataStore<ThemePreferences>
    private lateinit var themeRepository: ThemeRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic("dev.bwaim.loteria.theme.impl.ThemeRepositoryImplKt")

        every { mockContext.dataStore } returns mockDataStore

        themeRepository = ThemeRepositoryImpl(mockContext)

        mockkObject(BuildWrapper)
    }

    @Test
    fun shouldReturnThemeFlow_whenObserved() = runBlocking {
        every { BuildWrapper.SDK_INT } returns 29
        val expectedThemes = listOf(Theme.SYSTEM, Theme.LIGHT)
        val expectedThemePreferences = expectedThemes.map { theme ->
            ThemePreferences
                .newBuilder()
                .setTheme(theme.value)
                .build()
        }
        every { mockDataStore.data } returns flowOf(*expectedThemePreferences.toTypedArray())

        val themes = themeRepository.observeTheme().toList()

        Truth.assertThat(themes).isEqualTo(expectedThemes)
    }

    @Test
    fun shouldSaveTheme_whenSet() = runBlocking {
        val theme = Theme.DARK

        themeRepository.setTheme(theme)

        coVerify {
            mockDataStore.updateData(
                coWithArg { transform ->
                    val expectedThemePreferences = ThemePreferences
                        .newBuilder()
                        .setTheme(theme.value)
                        .build()
                    Truth.assertThat(transform(ThemePreferences.getDefaultInstance()))
                        .isEqualTo(expectedThemePreferences)
                }
            )
        }
    }
}

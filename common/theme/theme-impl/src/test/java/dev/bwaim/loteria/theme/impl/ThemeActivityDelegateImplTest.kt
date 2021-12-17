package dev.bwaim.loteria.theme.impl

import com.google.common.truth.Truth
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.ThemeService
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.Test

internal class ThemeActivityDelegateImplTest {
    @ExperimentalCoroutinesApi
    @Test
    fun shouldReturnInitialTheme_whenObserved() = runBlocking {
        mockkObject(BuildWrapper)
        every { BuildWrapper.SDK_INT } returns 29
        val mockThemeService = mockk<ThemeService>(relaxed = true).apply {
            every { observeTheme() } returns flowOf(ThemeHelper.defaultTheme)
        }
        val themeActivityDelegate = ThemeActivityDelegateImpl(
            applicationScope = TestScope(),
            themeService = mockThemeService
        )

        val theme = themeActivityDelegate
            .theme
            .first()

        Truth.assertThat(theme).isEqualTo(ThemeHelper.defaultTheme)
    }
}

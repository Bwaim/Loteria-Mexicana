package dev.bwaim.loteria.theme.impl

import com.google.common.truth.Truth
import dev.bwaim.loteria.core.utils.BuildWrapper
import dev.bwaim.loteria.theme.Theme
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test

internal class ThemeHelperTest {
    @Before
    fun setUp() {
        mockkObject(BuildWrapper)
    }

    @Test
    fun shouldReturnBatterySaver_whenApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        Truth.assertThat(ThemeHelper.defaultTheme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        Truth.assertThat(ThemeHelper.defaultTheme).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun shouldReturnBatterySaver_whenPreferenceIsEmptyAndApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences("")

        Truth.assertThat(theme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenPreferenceIsEmptyAndApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        val theme = ThemeHelper.fromPreferences("")

        Truth.assertThat(theme).isEqualTo(Theme.SYSTEM)
    }

    @Test
    fun shouldReturnBatterySaver_whenPreferenceIsSystemAndApiIs28() {
        every { BuildWrapper.SDK_INT } returns 28

        val theme = ThemeHelper.fromPreferences(Theme.SYSTEM.value)

        Truth.assertThat(theme).isEqualTo(Theme.BATTERY_SAVER)
    }

    @Test
    fun shouldReturnSystem_whenPreferenceIsBatterySaverAndApiIs29() {
        every { BuildWrapper.SDK_INT } returns 29

        val theme = ThemeHelper.fromPreferences(Theme.BATTERY_SAVER.value)

        Truth.assertThat(theme).isEqualTo(Theme.SYSTEM)
    }
}

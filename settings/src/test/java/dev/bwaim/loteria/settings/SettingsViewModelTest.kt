package dev.bwaim.loteria.settings

import app.cash.turbine.test
import com.google.common.truth.Truth
import dev.bwaim.library.test.MainCoroutineRule
import dev.bwaim.loteria.theme.Theme
import dev.bwaim.loteria.theme.ThemeService
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SettingsViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var settingsViewModel: SettingsViewModel

    @MockK(relaxUnitFun = true)
    lateinit var mockThemeService: ThemeService

    private val themes = listOf(Theme.LIGHT, Theme.DARK)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearMocks(mockThemeService)
    }

    @Test
    fun shouldUpdateState_whenThemeChanged() = runTest {
        initViewModel()

        settingsViewModel.viewState.test {
            Truth.assertThat(awaitItem()).isEqualTo(SettingsState())
            Truth.assertThat(awaitItem())
                .isEqualTo(SettingsState(appTheme = Theme.LIGHT, themes = themes))
            Truth.assertThat(awaitItem())
                .isEqualTo(SettingsState(appTheme = Theme.DARK, themes = themes))
        }
    }

    @Test
    fun setTheme_whenSet() {
        initViewModel()

        settingsViewModel.setTheme(Theme.SYSTEM)

        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { mockThemeService.setTheme(Theme.SYSTEM) }
    }

    private fun initViewModel() {
        every { mockThemeService.getThemes() } returns themes
        every { mockThemeService.observeTheme() } returns flowOf(Theme.LIGHT, Theme.DARK)

        settingsViewModel = SettingsViewModel(mockThemeService)
    }
}

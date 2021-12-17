package dev.bwaim.loteria.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import dev.bwaim.loteria.theme.Theme
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SettingsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var mockSettingsViewModel: SettingsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldShowSettings() {
        val themes = listOf(Theme.LIGHT, Theme.DARK)
        var themeTitle: String? = null
        var currentThemeLabel: String? = null

        every { mockSettingsViewModel.viewState } returns MutableStateFlow(
            SettingsState(
                appTheme = Theme.LIGHT,
                themes = themes
            )
        )

        testContent {
            Settings(
                viewModel = mockSettingsViewModel,
                navigateUp = { }
            )
            themeTitle = stringResource(id = R.string.settings_app_theme_title)
            currentThemeLabel = Theme.LIGHT.getLabel()
        }
        composeTestRule.onNode(hasText(themeTitle!!)).assertExists()
        composeTestRule.onNode(hasText(currentThemeLabel!!)).assertExists()
    }

    private fun testContent(call: @Composable () -> Unit) {
        composeTestRule.setContent {
            LoteriaTheme {
                call()
            }
        }
    }
}

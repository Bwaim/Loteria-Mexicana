package dev.bwaim.loteria.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AppNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var settingsButtonLabel: String
    private lateinit var settingsScreenTitle: String
    private lateinit var mainScreenTitle: String
    private lateinit var upArrowDescription: String

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @ExperimentalAnimationApi
    @Test
    fun shouldNavigateToSettings_whenSettingsClicked() {

        testContent {
            AppNavigation()
        }

        goToSettings()
    }

    @ExperimentalAnimationApi
    @Test
    fun shouldGoBackFromSettings_whenBackClicked() {
        testContent {
            AppNavigation()
        }

        goToSettings()
        goBack()
    }

    private fun testContent(call: @Composable () -> Unit) {
        composeTestRule.setContent {
            LoteriaTheme {
                SetLabels()
                call()
            }
        }
    }

    @Composable
    private fun SetLabels() {
        settingsButtonLabel = stringResource(id = R.string.settings)
        settingsScreenTitle = stringResource(id = R.string.settings_title)
        mainScreenTitle = stringResource(id = R.string.app_name)
        upArrowDescription = stringResource(id = R.string.toolbar_up_description)
    }

    private fun goToSettings() {
        composeTestRule.onNodeWithContentDescription(settingsButtonLabel).performClick()
        composeTestRule.onNodeWithText(settingsScreenTitle).assertIsDisplayed()
    }

    private fun goBack() {
        composeTestRule.onNodeWithContentDescription(upArrowDescription).performClick()
        composeTestRule.onNodeWithText(mainScreenTitle).assertIsDisplayed()
    }
}

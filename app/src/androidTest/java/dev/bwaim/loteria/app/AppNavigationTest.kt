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

package dev.bwaim.loteria.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.bwaim.loteria.compose.theme.LoteriaTheme
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalAnimationApi
@HiltAndroidTest
internal class AppNavigationTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var drawButtonLabel: String
    private lateinit var drawScreenTitle: String
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

    @ExperimentalAnimationApi
    @Test
    fun shouldNavigateToDraw_whenDrawClicked() {

        testContent {
            AppNavigation()
        }

        goToDraw()
    }

    @ExperimentalAnimationApi
    @Test
    fun shouldGoBackFromDraw_whenBackClicked() {
        testContent {
            AppNavigation()
        }

        goToDraw()
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
        drawButtonLabel = stringResource(id = R.string.start_menu)
        drawScreenTitle = stringResource(id = R.string.draw_title)
    }

    private fun goToSettings() {
        composeTestRule.onNodeWithContentDescription(settingsButtonLabel).performClick()
        composeTestRule.onNodeWithText(settingsScreenTitle).assertIsDisplayed()
    }

    private fun goToDraw() {
        composeTestRule.onNodeWithText(drawButtonLabel).performClick()
        composeTestRule.onNodeWithText(drawScreenTitle).assertIsDisplayed()
    }

    private fun goBack() {
        composeTestRule.onNodeWithContentDescription(upArrowDescription).performClick()
        composeTestRule.onNodeWithText(mainScreenTitle).assertIsDisplayed()
    }
}

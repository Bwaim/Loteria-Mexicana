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
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@ExperimentalAnimationApi
@HiltAndroidTest
internal class AppNavigationTest {

    @get:Rule(order = 0)
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Create a temporary folder used to create a Data Store file. This guarantees that
     * the file is removed in between each test, preventing a crash.
     */
    @BindValue
    @get:Rule(order = 2)
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private lateinit var drawButtonLabel: String
    private lateinit var drawScreenTitle: String
    private lateinit var settingsButtonLabel: String
    private lateinit var settingsScreenTitle: String
    private lateinit var mainScreenTitle: String
    private lateinit var upArrowDescription: String

    @Before
    fun setUp() {
        composeTestRule.activity.apply {
            drawButtonLabel = getString(dev.bwaim.loteria.home.R.string.start_menu)
            drawScreenTitle = getString(dev.bwaim.loteria.draw.R.string.draw_title)
            settingsButtonLabel = getString(dev.bwaim.loteria.home.R.string.settings)
            settingsScreenTitle = getString(dev.bwaim.loteria.settings.R.string.settings_title)
            mainScreenTitle = getString(R.string.app_name)
            upArrowDescription = getString(dev.bwaim.loteria.compose.R.string.toolbar_up_description)
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun firstScreen_isMain() {
        composeTestRule.apply {
            onNodeWithText(mainScreenTitle).assertIsDisplayed()
        }
    }

    @Test
    fun clickOnSettings_navigateToSettings() {
        composeTestRule.apply {
            goToSettings()
        }
    }

    @Test
    fun navigation_navigateToPreviousScreen_restoreScreen() {
        composeTestRule.apply {
            onNodeWithText(mainScreenTitle).assertIsDisplayed()
            goToSettings()
            goBack()
        }
    }

    @Test
    fun clickOnDrawButton_navigateToDrawScreen() {
        composeTestRule.apply {
            goToDraw()
        }
    }

    @Test
    fun navigatePreviousFromDrawScreen_navigateToMain() {
        composeTestRule.apply {
            goToDraw()
            goBack()
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun homeDestination_back_quitsApp() {
        composeTestRule.apply {
            // GIVEN the user navigates to the Settings destination
            goToSettings()
            // and then navigates to the Main destination
            goBack()
            onNodeWithText(mainScreenTitle).assertIsDisplayed()
            // WHEN the user uses the system button/gesture to go back
            Espresso.pressBack()
            // THEN the app quits
        }
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
    }
}
